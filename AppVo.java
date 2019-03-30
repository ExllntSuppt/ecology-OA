/*
 *  
 */
package weaver.blog;

public class AppVo {
    private String id;
    private String name;
    private String appType;
    private String sort;
    private String iconPath;
    private boolean isActive;

    public String getId() {
        return this.id;
    }

    public void setId(String string) {
        this.id = string;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String string) {
        this.name = string;
    }

    public String getAppType() {
        return this.appType;
    }

    public void setAppType(String string) {
        this.appType = string;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String string) {
        this.sort = string;
    }

    public String getIconPath() {
        return this.iconPath;
    }

    public void setIconPath(String string) {
        this.iconPath = string;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean bl) {
        this.isActive = bl;
    }
}