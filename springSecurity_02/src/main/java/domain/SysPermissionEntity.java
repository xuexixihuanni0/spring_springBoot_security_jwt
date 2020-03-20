package domain;

import lombok.Data;

@Data
public class SysPermissionEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -2165521275432675728L;

    /** id */
    private Integer id;

    /** permName */
    private String permName;

    /** permTag */
    private String permTag;


}