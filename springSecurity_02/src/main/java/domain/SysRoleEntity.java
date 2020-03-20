package domain;

import lombok.Data;

@Data
public class SysRoleEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 6853619219605180044L;

    /** id */
    private Integer id;

    /** roleName */
    private String roleName;

    /** roleDesc */
    private String roleDesc;


}