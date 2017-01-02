package com.sjg.sys.utils;

import java.util.HashMap;
import java.util.Map;

import com.sjg.sys.entity.User;

/**
 * 常工具类
 * @author huangliang
 *
 */
public class contantUtil {
	
	/**
	 * 保存session对象的个数
	 */
	public static long sessionCount = 0;

	/**
	 * 缓存信息(现用于缓存图标信息)
	 */
	public static Map<String, Object> CACHE = new HashMap<String, Object>();

    /**
     * 存储font-awesome矢量图的icon
     */
    public static final String FONTAWESOME_KEY = "FONT_ICON";

	/**
	 * 保存所有登录的用户
	 */
	public static Map<String, User> userMap = new HashMap<String, User>();
	
	/**
	 * 后台登录用户session 
	 */
	public static final String SESSION_USER="user";

	/**
	 * 资源类型：菜单
	 */
	public static final String RESOURCE_MENU="1";
	
	/**
	 * 资源根节点的PID
	 * @author huangliang
	 */
	public static final String RESOURCE_ROOTNODE_PID="0";
	
	/**
	 * 登录验证码
	 */
	public static final String RANDOMCODEKEY="ValidateCodeKey";
	
	//用户登录系统的类型
	/**
	 * 登录
	 */
	public static final String lOGIN_L = "LOGIN";
	/**
	 * 退出
	 */
	public static final String lOGIN_E = "EXIT";
	

	/**
	 * 小顶网图片 前缀
	 */
	public static final  String IMGURLPREFIX="http://o6yahvsqr.bkt.clouddn.com/";
	/**
	 * 图片前缀信息
	 */
	public static final  String HTTPPREFIX ="http://ocg0afzuw.bkt.clouddn.com/";
		
	/*字典表常量 start*/
	/**
	 * 会员级别 pid
	 */
	public static final Long MBLEVEL=31L;
	/**
	 * 会员来源 pid
	 */
	public static final Long MBFROM=33L;
	/**
	 * 商品标签 pid
	 */
	public static final Long GDLABEL=10L;
	/**
	 * 商标组合 pid
	 */
	public static final Long GDDBRANDTYPE=23L;
	/**
	 * 入住电商  pid
	 */
	public static final Long GDDONLINERETAILERS= 6L;
	/**
	 * 站点 code
	 */
	public static String SITE="site";

	/**
	 * 文章栏目
	 */
	public static String AECN="aecn";
	/**
	 * 关于我们
	 */
	public static String ABOUT="about";
	
	
	//公司相关字典 start
	/**
	 * 地理区域省市区县 具有上下级关系
	 */
	public static String ZONESET="zoneset";
	/**
	 * 公司发布标签
	 */
	public static String C_COMP_RELEASE_TAG="c_comp_release_tag";
	/**
	 * 投资主体
	 */
	public static String C_INVES_SUB="c_inves_sub";
	/**
	 *  纳税类型
	 */
	public static String C_TAX_TYPE="c_tax_type"; 
	/**
	 * 所属行业
	 */
	public static String C_OWN_INDU="c_own_indu"; 
	/**
	 *  无形资产
	 */
	public static String C_INTA_ASSET="c_inta_asset"; 
	/**
	 *  无形资产-商标
	 */
	public static final String C_BRAND = "c_brand";
	/**
	 *  无形资产-专利
	 */
	public static final String C_PATENT = "c_patent";
	/**
	 *  无形资产-发明
	 */
	public static final String C_INVENTION = "c_Invention";
	//公司相关字典 end
	
	
	/*字典表常量 end*/
	
	
	/*申请来源（1：商标  2：公司   3：专利）*/
	public static final Long TRADE = 1L;
	public static final Long COMPANY = 2L;
	public static final Long PATENT = 3L;
	
	public static final Long ATTRIBUTION_CONSULTING = 1016L;
	public static final Long SBZX = 40L;
	public static final Long ZLZX = 41L;
	public static final Long GSZX = 42L;
	
	
	/**
	 * 启用
	 */
	public static final Long ENABLED = 1L; 

	/**
	 * 停用
	 */
	public static final Long DISABLE = 2L;

	/**
	 * 所属网站
	 */
	public static final String SSWZ = "suoshuwangzhan";

	/**
	 * 所属行业
	 */
	public static final String SSHY = "c_own_indu";

}
