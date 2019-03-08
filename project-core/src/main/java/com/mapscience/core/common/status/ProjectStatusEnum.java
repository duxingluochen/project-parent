package com.mapscience.core.common.status;

/**
 * 全局状态编码
 */
public enum ProjectStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS(200, "success"),
    /**
     * 日期格式异常
     */
    INVLIDE_DATE_STRING(300, "输入日期格式不对"),
    /**
     * 请求异常
     */
    ERROR(500, "error"),

    /**
     * 字典
     */
    DICT_EXISTED(100, "字典已经存在"),
    ERROR_CREATE_DICT(101, "创建字典失败"),
    ERROR_WRAPPER_FIELD(102, "包装字典属性失败"),
    ERROR_CODE_EMPTY(103, "字典类型不能为空"),

    /**
     * 文件上传
     */
    FILE_NOT_FOUND(200, "FILE_NOT_FOUND!"),
    UPLOAD_ERROR(201, "上传图片出错"),
    FILE_READING_ERROR(202, "文件读取异常"),
    /**
     * 权限和数据问题
     */
    DB_RESOURCE_NULL(300, "数据库中没有该资源"),
    NO_PERMITION(301, "权限异常"),
    REQUEST_INVALIDATE(302, "请求数据格式不正确"),
    INVALID_KAPTCHA(303, "验证码不正确"),
    CANT_DELETE_ADMIN(304, "不能删除超级管理员"),
    CANT_FREEZE_ADMIN(305, "不能冻结超级管理员"),
    CANT_CHANGE_ADMIN(306, "不能修改超级管理员角色"),

    /**
     * 账户问题
     */
    USER_ALREADY_REG(600, "该用户已经注册"),
    NO_THIS_USER(601, "没有此用户"),
    USER_NOT_EXISTED(602, "没有此用户"),
    ACCOUNT_FREEZED(603, "账号被冻结"),
    OLD_PWD_NOT_RIGHT(604, "原密码不正确"),
    TWO_PWD_NOT_MATCH(605, "两次输入密码不一致"),

    /**
     * 错误的请求
     */
    MENU_PCODE_COINCIDENCE(700, "菜单编号和副编号不能一致"),
    EXISTED_THE_MENU(701, "菜单编号重复，不能添加"),
    DICT_MUST_BE_NUMBER(702, "字典的值必须为数字"),
    REQUEST_NULL(703, "请求有错误"),
    SESSION_TIMEOUT(704, "会话超时"),
    SERVER_ERROR(705, "服务器异常"),
    WRITE_ERROR(500, "渲染界面错误"),

    /**
     * token异常
     */
    TOKEN_EXPIRED(800, "token过期"),
    TOKEN_ERROR(801, "token验证失败"),

    /**
     * 签名异常
     */
    SIGN_ERROR(900, "签名验证失败"),

    /**
     * 其他
     */
    AUTH_REQUEST_ERROR(1000, "账号密码错误"),

    /**
     * 设备库存使用问题
     */
    FAC_STOCK_USE_ERROR(1100, "该库存设施已分配使用，不能删除！"),

    /**
     * 流程问题
     */
    FLOW_REALY_APPLY(1200, "该节点已被申领"),
    FLOW_REALY_ACCESS(1201, "该节点已流转"),
    FLOW_REALY_REJECT(1202, "该节点已驳回");

    /**
     * 系统码
     */
    private int code;



    /**
     * 描述
     */
    private String msg;
    ProjectStatusEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }


    public static ProjectStatusEnum getStatusByKey(int key) {
        for (ProjectStatusEnum t : values()) {
            if (t.getCode() == key) {
                return t;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
