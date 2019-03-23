/*
 *  
 */
package weaver.blog;

public class AppItemVo {
    private String id;
    private String itemName;
    private String value;
    private String type;
    private String faceImg;

    public String getId() {
        return this.id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getValue() {
        return this.value;
    }

    public void setId(String string) {
        this.id = string;
    }

    public void setItemName(String string) {
        this.itemName = string;
    }

    public void setValue(String string) {
        this.value = string;
    }

    public void setType(String string) {
        this.type = string;
    }

    public String getType() {
        return this.type;
    }

    public String getFaceImg() {
        return this.faceImg;
    }

    public void setFaceImg(String string) {
        this.faceImg = string;
    }
}