package common.utils;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.danga.MemCached.Logger;


import freemarker.template.utility.StringUtil;

/**
 * EPUB文件操作工具类
 * @author lixinglei
 * @date 2012-04-12
 * @description 封装对于epub文件操作的各种常用方法
 */
public class EpubUtil {

	public static final String EPUB_SUFFIX = ".epub";
	
	public static final String NCX_SUFFIX = ".ncx";
	
	public static final String OPF_SUFFIX = ".opf";
	
	public static final String SERVER_FILE_PATH = "D:/Books/-1/Epub";
	
	public static final String EPUB_ENTRY = "META-INF/container.xml";
	
	private static final String ENCODING = "UTF8";
	
	/**
     * 文件路径分隔符
     */
    public static final String FILE_SEPARATOR = "/";
    
    /**
     * windows下的文件路径分隔符
     */
    public static final String FILE_SEPARATOR_WINDOWS = "\\";
    
	/**
	 * 检查指定文件是否为epub格式文件
	 * @param epubFileName
	 * @return
	 */
	public static boolean isEpub(String epubFileName){
		if(!FileUtil.isEmpty(epubFileName) || epubFileName.endsWith(EPUB_SUFFIX)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取要上传到的服务器的全路径
	 * @return
	 */
	public static String getUploadFullPath(){
		return SERVER_FILE_PATH + FILE_SEPARATOR + getNow("yyyyMMdd")
				+ FILE_SEPARATOR + System.currentTimeMillis();
	}
	
	
	
	/**
	 * 根据指定的epub文件路径解压缩ncx目录文件到当前目录
	 * @param epubFileName
	 */
	public static void uncompressEpubNcx(String epubFileName){
		ZipFile zipFile = null;
		InputStream in = null;
		OutputStream out = null;
		try {
            zipFile = new ZipFile(new File(epubFileName));
            in = getNcxfile(epubFileName);
            File ncxFile = new File(epubFileName.replace(EPUB_SUFFIX,NCX_SUFFIX));
            out = new FileOutputStream(ncxFile);
            FileUtil.copy(in, out);
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        	FileUtil.close(in);
        	FileUtil.close(zipFile);
        }
	}
	
	/**
     * 获得指定epub书中的ncx文件的字节流（需要重构）
     * @param filePath epub书的绝对路径
     * @return 没找到文件返回null，有则返回字节流
     * @throws Exception
     */
    public static InputStream getNcxfile(String filePath) throws Exception {

		// 只处理epub书，后缀为.epub.
		if (null != filePath && -1 != filePath.lastIndexOf(".epub")) {

			// 解析zip文件，从中获得.ncx文件流
			ZipFile zipFile = new ZipFile(filePath);
			Enumeration es = zipFile.entries();

			// 待输出的ncx文件流
			InputStream in = null;

			// container.xml中指定的ncx路径
			String ncxPath = null;

			// 一个epub文件中可能有多个ncx文件，保存ncx的zipentry对象
			List<ZipEntry> ncxZipList = new ArrayList<ZipEntry>();

			while (es.hasMoreElements()) {
				ZipEntry ze = (ZipEntry) es.nextElement();

				// 1、 找到container.xml文件，进行文件解析处理
				if (-1 != ze.getName().lastIndexOf(
						"META-INF/" + "container.xml")) {
					SAXReader reader = new SAXReader();

					// 忽略对文件的校验
					reader.setEntityResolver(new EntityResolver() {
						ByteArrayInputStream bytels = new ByteArrayInputStream(
								"".getBytes());

						public InputSource resolveEntity(String publicId,
								String systemId) throws SAXException,
								IOException {
							return new InputSource(bytels);
						}
					});

					// 将文件流转换为Document对象进行解析
					Document document = reader.read(zipFile.getInputStream(ze));
					Map<String, String> map = new HashMap<String, String>();
					map.put("container",
							"urn:oasis:names:tc:opendocument:xmlns:container");

					// ncx信息
					XPath x = document.createXPath("//container:rootfiles");
					x.setNamespaceURIs(map);

					Element rootfiles = (Element) x.selectSingleNode(document);
					Iterator<Element> iterator = rootfiles
							.elementIterator("rootfile");

					while (iterator.hasNext()) {
						Element subElement = iterator.next();

						// 获得第一个full-path不为空的值，便为.opf文件的路径值，ncx文件在同路径下
						if (subElement.attribute("full-path") != null) {
							ncxPath = subElement.attribute("full-path")
									.getValue();
							break;
						}
					}
				}

				// 为ncx文件，则保存到list中。
				if (ze.getName().endsWith(".ncx")) {
					ncxZipList.add(ze);
				}
			}

			// 如果获得了.opf的文件路径
			if (null != ncxPath && !"".equals(ncxPath)) {
				for (ZipEntry ncxZe : ncxZipList) {
					// 通过.opf文件路径，获得ncx的上层目录
					String ncxDir = FileUtil.substringBeforLast(ncxPath,
							System.getProperty("file.separator"));

					// 查找匹配的ncx文件，返回字节流。
					if (-1 == FileUtil.substringAfterLast(ncxZe.getName(), ncxDir + System.getProperty("file.separator")).indexOf(System.getProperty("file.separator"))) {
						in = zipFile.getInputStream(ncxZe);
						break;
					}
				}
				return in;
			}
		}
		return null;
	}
	
    public static List<Element> getElementByName(Element rootElement, String elementName){
    	List<Element> elementList = rootElement.elements();
    	List<Element> navPointList = new ArrayList<Element>();  
    	if(elementList != null && elementList.size() > 0){
    		for(int i=0;i<elementList.size();i++){
    			Element tempElement = elementList.get(i);
    			
    			if(elementName.equals(tempElement.getName())){
    				navPointList.add(tempElement);
    			}
    			navPointList.addAll(getElementByName(tempElement, elementName));
    		}
    	}
    	
    	return navPointList;
    }
    
 
	
	

	
	/**
     *  从一个ncxFile中获取章节文件列表,从第startChapter开始，不含第startChapter章
     * @param ncxFileName
     * @param startChapter
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public static List<String> getChapterRightsList(String ncxFileName, int startChapter) throws Exception{
		SAXReader saxReader = new SAXReader();
		saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		Document ncxDocument = saxReader.read(new File(ncxFileName));
		List<Element> navPointList = getElementByName(ncxDocument.getRootElement(),"navPoint");
		
		List<String> list = new ArrayList<String>();
		//String serverPath = FileUtil.substringBeforLast(ncxFileName, FILE_SEPARATOR);
		//String serverPath = getServerPath(ncxFileName);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(navPointList != null && navPointList.size()>0){
			for(int j=0;j<navPointList.size();j++){
				String chapterSrc =navPointList.get(j).element("content").attributeValue("src");
				//list.add(chapterSrc);
				if(chapterSrc.indexOf("#") > -1){
					chapterSrc =FileUtil.substringBeforLast(navPointList.get(j).element("content").attributeValue("src"), "#") ;
				}
				//../oebps/tom0001/tom0001.html
				map.put(chapterSrc, chapterSrc);
			}
			Iterator  it = map.keySet().iterator();
			while (it.hasNext()) {
				list.add(""+map.get(it.next()));
			}
		}

		if(startChapter<0){
			startChapter = 0;
		}
		if(list.size()<startChapter){
			return null;
		}
		return list.subList(startChapter, list.size());
	}
	
	
	
	
	
	/**
	 * 从指定文件夹中获取epub书本的ncx文件路径（需要重构）
	 * @param containerFilePath
	 * @return
	 */
	public static String getNcxFromDir(String targetDirPath) throws Exception{
		
		List<String> ncxList = FileUtil.getFilesFromDir(targetDirPath, NCX_SUFFIX);
		
		//如果不存在ncx文件则说明不包含目录文件——少数情况epub不包含ncx目录文件，阅读器一样可以解析
		if(ncxList == null || ncxList.size() == 0){
			return null;
		}
		//如果只有一个ncx文件，则该文件就是本书的目录文件——多数情况属于此类
		else if(ncxList.size() == 1){
			return ncxList.get(0);
		}
		//极少情况有存在多个ncx目录的
		else{
			//获取入口文件META-INF/container.xml
			String containerFilePath = getContainerFromEpubDir(targetDirPath);
			if(!FileUtil.isEmpty(containerFilePath)){
				SAXReader saxReader = new SAXReader();
				Document containerDocument = saxReader.read(new File(containerFilePath));
				
				String opfPath = null;
				List<Element> rootfileList = containerDocument.getRootElement().element("rootfiles").elements();
				
				for(int i=0; i<rootfileList.size(); i++){
					//获得第一个full-path不为空的值，便为.opf文件的路径值，ncx文件在同路径下
					if(rootfileList.get(i).attributeValue("full-path") != null){
						opfPath = rootfileList.get(i).attributeValue("full-path");
						break;
					}
				}
				
				//获取ncx的路径
				opfPath = targetDirPath + FILE_SEPARATOR +
					FileUtil.substringBeforLast(opfPath, FILE_SEPARATOR);
				for(int i=0;i<ncxList.size();i++){
					//获取在同路径下的ncx路径
					if(ncxList.get(i).startsWith(opfPath)){
						return ncxList.get(i);
					}
				}
				
				//如果没有同目录下的，则返回第一个
				return ncxList.get(0);
			}else{
				//如果没有获取到container.xml文件，则返回第一个（此处可以考虑直接获取opf文件的位置来定夺）
				return ncxList.get(0);
			}
		}
	}
	
	/**
	 * 从解压出来的epub文件夹中获取"META-INF/container.xml"的路径
	 * @param epubDirPath
	 * @return
	 */
	public static String getContainerFromEpubDir(String epubDirPath)throws IOException{
		if(FileUtil.isEmpty(epubDirPath)){
			return null;
		}
		List<String> containerList = FileUtil.getFilesFromDir(epubDirPath, EPUB_ENTRY);
		if(containerList == null || containerList.size() == 0){
			return null;
		}else
			return containerList.get(0);
	}
    
	/**
	 * 将指定的文件夹epubDir压缩为名为epubName的epub文件，并删除该文件夹
	 * @param epubDir
	 * @param epubName
	 */
	public static void compressEpub(String epubDir,String epubName){
		
	}
	
	
	
	
	
	
	/**
	 * 判断一个章节是否已经加密过
	 * @param epubFileName
	 * @return
	 */
	public static boolean isEncrypted(String epubFilePath, String chapter){
		return true;
	}
	
	/**
	 * 对指定的章节进行加密
	 * @param epubFileName
	 * @param startChapter
	 */
	private static void encryptChapter(String epubFilePath, String startChapter){
		
	}
	
	/**
	 * 根据指定格式pattarn返回系统当前时间.
	 * 
	 * 如pattarn="yyyyMMdd" 则返回"20120327"
	 * 其中，yyyy:year, MM:month, dd:day, hh:hour, mm:minute, ss:second
	 */
	private static final String getNow(String pattarn){
		if(FileUtil.isEmpty(pattarn)){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		}
		return new SimpleDateFormat(pattarn).format(Calendar.getInstance().getTime());
	}
}
