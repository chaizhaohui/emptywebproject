package common.utils;



/**
 * 返回码常量。 1XX: request received. Asynchronous operations. 2XX: request received and processed successfully. 3XX: request
 * received but need further action. 4XX: client request error. The request is not an valid request. 5XX: server side
 * error.
 * 
 * @author 吴飞00106856
 * @version [1.0, 2009-1-1]
 * 
 * @see [相关类/方法]
 * @since [iRead/Server1.0]
 */
public interface TerminalCodes
{
    // -------------------1XX begin-------------------------//
    // -------------------1XX end---------------------------//
    
    // -------------------2XX begin-------------------------//
    /** 请求结果成功 */
    public static final int PAGE_REQUEST_SUCCESS = 200;
    
    /** 请求结果成功，无响应消息 */
    public static final int SUCCESS_NO_RESPONSE = 204;
    
    // -------------------2XX end---------------------------//
    
    // -------------------3XX begin-------------------------//
    /**
     * Need to input verification code to complete the registration
     */
    public static final int NEED_VERIFYCODE = 300;
    
    /** 内容信息没有修改 */
    public static final int CONTENT_NO_MODIFY = 302;
    
    /** 用户的账号通过WAP GW自动注册 */
    public static final int REGISTER_AUTOBY_WAPGW = 320;
    
    /** 全站包月产品下载次数达到上限 */
    public static final int DOWNLOAD_TIME_LIMIT = 321;
    
    // -------------------3XX end---------------------------//
    
    // -------------------4XX begin-------------------------//
    
    /** 其他客户端错误 */
    public static final int OTHER_CLIENT_ERROR = 400;
    
    /** 未鉴权 */
    public static final int UNAUTHORIZEDR = 401;
    
    /** 请求的服务不存在 */
    public static final int INVALID_REQUEST_SERVICE = 404;
    
    /** 设备鉴权失败 */
    public static final int DEVICE_UNAUTHORIZED = 420;
    
    /** 用户鉴权失败 */
    public static final int USER_UNAUTHORIZED = 421;
    
    /** 参数非法/Invalid parameter. */
    public static final int INVALID_PARAMETER = 422;
    
    /** 内容鉴权失败 */
    public static final int CONTENT_UNAUTHORIZED = 423;
    
    /** 用户ID非法/Invalid userId */
    public static final int INVALID_USERID = 424;
    
    /** Email非法/Invalid email */
    public static final int INVALID_EMAIL = 425;
    
    /** 用户已存在/User has already been registered */
    public static final int USER_REGISTER_ALREADY = 426;
    
    /** 用户ID不存在/UserId is not exist */
    public static final int USER_NOT_EXIST = 427;
    
    /** 专区不存在 */
    public static final int CATALOG_NOT_EXIST = 428;
    
    /** position非法(修改收藏夹) */
    public static final int INVALID_POSITION = 429;
    
    /** 内容未收藏 */
    public static final int CONTENT_NOT_FAVORITE = 430;
    
    /** 订购内容时，订购开始时间和结束时间超出产品有效期 */
    //public static final int TIME_OUT_RANGE = 431;
    
    /** 验证码过期*/
    public static final int VERIFYCODE_INVALIDA = 431;
    
    /** 订购内容时，订购开始时间和结束时间超出产品时间范围 */
    //public static final int SUBSCRIBE_TIME_OUT_RANGE = 432;
    
    /** 客户端已是最新版本 */
    public static final int CLIENT_NEW_VERSION = 433;
    
    /** 用户下载到达10次 */
   // public static final int DOWNTIMEOUTOFTEN_CLIENT_ERROR = 434;
    
    /** 无效的URL */
    public static final int IVALID_URL = 435;
    
    /** 收藏超过最大限制 */
    public static final int USER_ASSFAVORITE_MAX = 436;
    
    /** 用户未注册 */
    public static final int USER_NOT_REGISTER = 437;
    
    /** 专区目录没有变化 */
    public static final int CATALOG_NOT_CHANGE = 438;
    
    /** push开关已关闭 */
    public static final int PUSH_SWITCH_CLOSED = 439;
    
    /** SMS开关已关闭 */
    public static final int SMS_SWITCH_CLOSED = 440;
    
    /** 按次订购如传递时间过来，则报错：订购失败,多余的参数 */
    public static final int SUBSCRIBE_MORE_PARAMETER = 441;
    
    /**
     * 请求时间大于最后一次修改时间
     */
    //public static final int REQUEST_TIMEABOVE_LASTMODIFYTIME = 442;
    
    /** 用户Id已存在，密码错误 */
    public static final int USERID_PASSWORD_NOT_MATCH = 443;
    
    /** 该设备已绑定其他用户 */
    public static final int DEVICE_BLIND_USER = 444;
    
    /** Voucher信息不存在 */
    public static final int VOUCHER_NOT_EXIST = 448;
    /**书城首页文字推荐内容为空*/
    public static final int BOOKSTOREINDEXLITRECOMMEND=447;
    /** 用户绑定的设备数已超过5个 */
    public static final int EXCEED_MAX_COUNT_OF_BIND_DEVICE = 449;
    
    /** 不支持当前的客户端版本 */
    public static final int NOT_SUPPORT_APIVERSION = 450;
    
    /** 订购开始时间小于系统当前时间 */
    public static final int START_BEFORE_SYSDATE = 451;
    
    /** 超过可下载次数 */
    public static final int MORE_THAN_DOWNLOAD_TIMES = 452;
    
    /** Frozen user */
    public static final int FROZEN_USER = 453;
    
    /** 注册用户id和请求头中号码不一致 */
    public static final int USERID_NOT_SUIT = 454;
    
    /** 超过订阅最大数 */
    public static final int EXCEED_RENT_LIMIT = 455;
    
    /** 用户被锁定状态 */
    public static final int USER_LOCKED = 456;
    
    /** Email地址或手机号码已经被用户使用过 */
    public static final int EMAIL_PHONE_ALREADY_USED = 457;
  	    
    /** 重置密码时,事务不存在 */
    public static final int REQUESTTIME_NOT_EXIST = 478;
    
    /** 用户注册时，IP无效 */
    public static final int INVALID_IP = 479;
    
    /** 用户注册时， 账号非法 */
    public static final int INVALID_ACCOUNT = 480;
    
    /** 新电局点，用户在订购时使用了失效的TSI */
    public static final int INVALID_TSI = 481;
    
    /** 删除用户标签失败 */
    public static final int DEL_USERLABEL_FAIL = 482;
    
    /** 请求资源不存在 */
    public static final int QEQ_RESOURCE_VALID = 483;
        
    // -------------------4XX end---------------------------//
    
    // -------------------5XX begin-------------------------//
    /**标签不存在*/
    public static final int UNKNOWN_lABEL_EEROR=511;
    /**标签已经添加*/
    public static final int HAVE_lABEL_EEROR=512;
    
    /**收一本书失败，稍后重试*/
    public static final int AROUND_BOOK_NOT_EXIST = 513;
    
    /**漂一本书失败，稍后重试*/
    public static final int AROUND_BOOK_SENT_ERROR = 513;
    
    /**收一本书回应更新失败，稍后重试*/
    public static final int RECEIVE_BOOK_UPDATE_ERROR = 513;
    
    /**关注失败*/
    public static final int ADD_FRIEND_ERROR = 513;
    
    /**取消关注失败*/
    public static final int DEL_FRIEND_ERROR = 513;
    
    /**没有被别人关注*/
    public static final int FANS_NOT_EXIST = 514;
    
    /**没有关注其他用户*/
    public static final int INTEREST_USER_NOT_EXIST = 515;
    
    /**漂一本书失败，传入参数错误，书名为空*/
    public static final int AROUND_BOOK_SENT_NULL_ERROR = 516;
    
    /**书籍不存在*/
    public static final int BOOK_NOT_EXISTS = 517;
    
    /** 未知服务端错误 */
    public static final int UNKNOWN_SERVER_ERROR = 500;
    
    /**用户未激活*/
    public static final int USER_NOT_ACTIVED = 519;
    
    /** 内容信息不存在 */
    public static final int CONTENT_INFO_NOT_EXIST = 520;
    
    /** 内容项信息不存在 */
    public static final int CONTENT_ITEM_INFO_NOT_EXIST = 520;
    
    /** 作家信息不存在 */
    public static final int AUTHOR_INFO_NOT_EXIST = 522;
    
    /** 专区没有子专区 */
    public static final int CATALOG_HAS_NOCHILD = 523;
    
    /** 内容已经被收藏(增加收藏夹内容时) */
    public static final int CONTENT_FAVORITED = 524;
    
    /** 内容下不存在该产品 */
    public static final int PRODUCT_OF_CONTENT_NOT_EXIST = 525;
    
    /** 专区下不存在该产品 */
    public static final int PRODUCT_OF_CATALOG_NOT_EXIST = 576;
    
    /** 产品不存在或已失效 */
    public static final int PRODUCT_NOT_EXIT_OR_INVALID = 526;
    
    /** 产品已被订购(订购时) */
    public static final int PRODUCT_SUBSCRIBED = 527;
    
    /** 产品未被订购(取消订购时) */
    public static final int PRODUCT_NOT_SUBSCRIBE = 528;
    
    /** 产品已经取消订购(取消订购时) */
    public static final int PRODUCT_UNSUBSCRIBED = 529;
    
    /** 产品不能取消订购 */
    public static final int PRODUCT_CANNOT_CANCEL_SUBSCRIBE = 530;
    
    /** 消息信息部存在 */
    public static final int MESSAGE_INFO_NOT_EXIST = 531;
    
    /** 增加远程下载地址出错 */
    //public static final int ADDDOWNURL_SERVER_ERROR = 532;
    
    /** 订购时间不在产品有效期内 */
    public static final int SUBSCRIBE_TIME_OUT = 534;
    
    /** 订购时长与产品不一致 */
    public static final int SUBSCRIBE_TIME_NOT_SAME_PRODUCT = 535;
    
    /** 取消订购时间超出产品允许的取消订购时间 */
    public static final int UNSUBSCRIBE_OUT_OF_TIME = 537;
    
    /** 按次订购的产品不能取消订购 */
    public static final int PRODUCT_CANNOT_SUBSCRIBE_BY_TIME = 539;
    
    /** 平台没有可下载的内容 */
    public static final int DOWNLOAD_CONTENT_NULL = 540;
    
    /** 要删除的评论不存在 */
    public static final int REVIEW_INFO_NOT_EXIST = 541;
    
    /**
     * 内容信息没有变化
     */
    //public static final int CONTENTINFO_NOT_CHANGE = 541;
    
    /**
     * 内容项信息没有变化
     */
    //public static final int CONTENTITEM_NOT_CHANGE = 542;
    
    /**
     * 用户Email为空
     */
    public static final int USER_EMAIL_IS_EMPTY = 543;
    
    /** 该用户的支付方式存在默认值 */
    public static final int EXTEND_DEFAULT_VALUE_ERROR = 544;
    
    /** 修改用户配送地址主键id必须为长整形 */
    public static final int MODIFY_SHIPPING_ADDR_ID_INVALID = 549;
    
    /** 未找到主键Id对应的用户配送地址 */
    public static final int SHIPPING_ADDR_DOES_NOT_EXISTS = 550;
    
    /** 删除记录不存在 */
    public static final int DELETE_RECORD_NOT_EXISTS_BYID = 554;
    
    /** 要删除的记录，没有全部删除 */
    public static final int RECORD_NOTALL_DELETE = 555;
    
    /** reviewId不存在 */
    public static final int REVIEWID_NOT_EXIST = 556;
    
    /** 无效的paymentMethodId */
    public static final int INVALIDE_PAYMENTMETHOD = 557;
    
    /** 无效的useVoucher */
    public static final int INVALIDE_USERVOUCHER = 558;
    
    /** 兑换券不支持兑换该产品 */
    public static final int INVALID_CONSUME_PRODUCTID = 559;
    
    /** 购买的产品所包含的Content数量超多兑换券可兑换的数量 */
    public static final int CONTENT_MORE_THAN_CONSUME_LEFTNUM = 560;
    
    /** 绑定voucher失败 */
    //public static final int ACCEPT_VOUCHER_FAILED = 561;
    
    /** 该券不能转赠 */
    public static final int TRANSFER_VOUCHER_ERROR = 562;
    
    /** voucher已经绑定用户 */
    public static final int VOUCHER_BINDED = 563;
    
    /** 当前用户无权修改或删除当前评论 */
    public static final int REVIEW_CAN_NOT_MODIFY_BY_CURRENT_USER = 564;
    
    /** 内容评论不存在 */
    public static final int REVIEW_NOT_EXIT = 565;
    
    /** 要修改的记录不存在 : 供书签好批注用 */
    public static final int MODIFY_RECORD_NOT_EXISTS_BYID = 566;
    
    /** 产品购买周期超过兑换券可兑换周期 */
    public static final int MORE_THAN_CONSUME_PERIOD = 567;
    
    /** 要删除的记录不存在 */
    public static final int DELETE_RECORD_NOT_EXIST = 568;
    
    /** 按照用户删除所有书签，该用户下没有书签 */
    public static final int DELETE_ALL_BOOKMARK_NOT_EXIST_BY_USERID = 569;
    
    /**  HTTP连接数过多 */
    public static final int HTTP_OVERLOAD = 570;
    
    /**  HTTP连接数过多 */
    public static final int LICENSE_OUT_LIMIT = 571;  
    
    /**  HTTP连接数过多 */
    public static final int LICENSE_OVERDUE = 572;  
    
    /**内容已经存在*/
    public static final int BOOK_EXITS =573;
    
    //--------------------DRM相关 start---------------------------//
    
    /**获取激活码失败*/
    public static final int DRM_FAIL_TO_GET_REGCODE =  580;
    
    /** 获取许可证失败 */
    public static final int GET_DRM_TICKET_FAILED = 581;
    
    
    /** 版权规则正在同步 */
    public static final int RIGHTS_RULE_BEING_SYS = 582;

    /** 请求连接数超过阀值 */
    public static final int REQUEST_CONNECTIONS_ABOVE_THRESHOLD = 583;
    
    //---------------------DRM 相关 end -------------------------//
    
    /** 系统数据库访问异常 das 错误统计用该异常码 */
    public static final int SYSTEM_DAS_EXCEPTION = 590;
    
    /** 无效的下载transactionId */
    public static final int INVALID_DOWNLOAD_TRANSACTIONID = 591;
    
    /** 下载的内容是非正式状态 */
    public static final int NOT_NORMAL_CONTENT = 592;
    //----------------PushMessage相关---------------
    /** PushMessage出错 */
    public static final int GET_PUSHMSG_FAILED = 593;
    
    /** 获取用户绑定的设备状态出错 */
    public static final int GET_DEVICE_STATUS_FAILED = 594;
    
    /** 获取已订购的内容列表失败 */
    //public static final int GET_PURCHASED_CONTENT_FAILED = 595;
    
    /** 获取已订购的内容项列表失败 */
    // public static final int GET_PURCHASED_CONTENTITEM_FAILED = 596;
    
    /** 增加邮件和sm发送失败结果码 */
    public static final int FAILED_TO_SEND = 597;
    
  //----------------PushMessage相关end---------------
    
    /**  调用Charge计费失败 */
    public static final int CHARGE_BILL_FAILED = 599;
    
    // -------------------5XX end---------------------------//   
    
    /** 不支持该文件格式 */
    public static final int NOT_SUPPORT_FILETYPE = 600;
    
    /** 不支持该图片格式 */
    public static final int NOT_SUPPORT_IMGTYPE = 601;
    
    

    //-----------------------sns start 1000-1999 --------------------------//
    
    /** 当前用户无权更改当前内容! */
    public static final int SNS_CANNOT_MODIFY_BY_CURRENT_USER = 1000;
    
    /** 当前记录已删除! */
    public static final int SNS_IS_DELETED = 1001;
    
    /** 当前记录不存在! */
    public static final int SNS_NOT_EXISTS = 1002;
    //-----------------------sns end  1000-1999--------------------------//
}
