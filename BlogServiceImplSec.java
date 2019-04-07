/*
 *  
 * 
 *  
 *  weaver.blog.BlogDiscessVo
 *  weaver.blog.BlogReplyVo
 *  weaver.blog.webservices.BlogUserInfo
 *  weaver.general.WebserIntefaceSecurity
 */
package weaver.blog.webservices;

import weaver.blog.BlogDiscessVo;
import weaver.blog.BlogReplyVo;
import weaver.blog.webservices.BlogServiceImpl;
import weaver.blog.webservices.BlogUserInfo;
import weaver.general.WebserIntefaceSecurity;

public class BlogServiceImplSec
extends BlogServiceImpl {
    @Override
    public BlogUserInfo[] getAttentionList(String string, int n, int n2, int n3) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.getAttentionList(string, n, n2, n3);
    }

    @Override
    public BlogDiscessVo getBlogDiscessVo(String string, String string2, String string3) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.getBlogDiscessVo(string, string2, string3);
    }

    @Override
    public BlogDiscessVo[] getBlogDiscussListByTime(String string, int n, int n2, String string2) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.getBlogDiscussListByTime(string, n, n2, string2);
    }

    @Override
    public BlogDiscessVo[] getBlogDiscussListByTime(String string, int n, int n2) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.getBlogDiscussListByTime(string, n, n2);
    }

    @Override
    public int getBlogDiscussListByTimeCount(String string, String string2) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return -1;
        }
        return super.getBlogDiscussListByTimeCount(string, string2);
    }

    @Override
    public int getBlogDiscussListByTimeCount(String string) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return -1;
        }
        return super.getBlogDiscussListByTimeCount(string);
    }

    @Override
    public BlogDiscessVo[] getCommentList(String string) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.getCommentList(string);
    }

    @Override
    public BlogDiscessVo[] getDiscussListByDate(String string, String string2, String string3, String string4) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.getDiscussListByDate(string, string2, string3, string4);
    }

    @Override
    public String[] getMenuItemCount(String string) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.getMenuItemCount(string);
    }

    @Override
    public int getMyAttentionCount(String string) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return -1;
        }
        return super.getMyAttentionCount(string);
    }

    @Override
    public BlogUserInfo[] getMyAttentionList(String string) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.getMyAttentionList(string);
    }

    @Override
    public BlogUserInfo getUserInfo(String string) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.getUserInfo(string);
    }

    @Override
    public int isSubmit(String string, String string2) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return -1;
        }
        return super.isSubmit(string, string2);
    }

    @Override
    public BlogDiscessVo saveBlogDiscuss(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.saveBlogDiscuss(string, string2, string3, string4, string5, string6, string7, string8);
    }

    @Override
    public BlogDiscessVo saveBlogDiscuss(String string, String string2, String string3, String string4, String string5) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.saveBlogDiscuss(string, string2, string3, string4, string5);
    }

    @Override
    public BlogReplyVo saveBlogReply(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return null;
        }
        return super.saveBlogReply(string, string2, string3, string4, string5, string6, string7, string8);
    }

    @Override
    public void sendSubmitRemind(String string, String string2, String string3) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            super.sendSubmitRemind(string, string2, string3);
        }
    }

    @Override
    public int viewRight(String string, String string2) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            return -1;
        }
        return super.viewRight(string, string2);
    }

    @Override
    public void writeBlogReadFlag(String string, String string2, String string3) {
        boolean bl = WebserIntefaceSecurity.security((String)(string + ""));
        if (!bl) {
            super.writeBlogReadFlag(string, string2, string3);
        }
    }
}