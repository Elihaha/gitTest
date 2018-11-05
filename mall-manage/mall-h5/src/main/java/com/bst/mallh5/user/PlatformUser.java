package com.bst.mallh5.user;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 平台方用户信息
 * Auto-generated: 2018-09-20 15:21:7
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PlatformUser implements Serializable, AuthCachePrincipal {

    private static final long serialVersionUID = -1764813265911554204L;

    private String userId;

    private Long plainUserId;

    private Long currentUserId;

    private String userName;

    private int userType;

    private int comefrom;

    private String webUrl;

    private String accessCookie;

    private String nickName;

    private String wxNickName;

    private int gender;

    private String avatar;

    private boolean isBandPhone;

    private boolean isBandWx;

    private boolean isBandCseeAccount;

    private String gesturePassword;

    private String bandPhone;

    private String regionalName;

    private String areaName;

    private String schoolName;

    private String classroomName;

    private int regionalAuthority;

    private int prvId;

    private int cityId;

    private int countyId;

    private int schoolId;

    private int grade;

    private int classroomId;

    private int townsId;

    private String townsName;

    private String indexUrl;

    private String discoveryUrl;

    private String schoolYearData;

    private int accountType;

    private String relationId;

    private List<Long> relationUserIds;

    private String trueName;

    public String userUniqueFlag() {
        return accountType + "-" + plainUserId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setPlainUserId(Long plainUserId){
        this.plainUserId = plainUserId;
    }
    public Long getPlainUserId(){
        return this.plainUserId;
    }
    public void setCurrentUserId(Long currentUserId){
        this.currentUserId = currentUserId;
    }
    public Long getCurrentUserId(){
        return this.currentUserId;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserType(int userType){
        this.userType = userType;
    }
    public int getUserType(){
        return this.userType;
    }
    public void setComefrom(int comefrom){
        this.comefrom = comefrom;
    }
    public int getComefrom(){
        return this.comefrom;
    }
    public void setWebUrl(String webUrl){
        this.webUrl = webUrl;
    }
    public String getWebUrl(){
        return this.webUrl;
    }
    public void setAccessCookie(String accessCookie){
        this.accessCookie = accessCookie;
    }
    public String getAccessCookie(){
        return this.accessCookie;
    }
    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    public String getNickName(){
        return this.nickName;
    }
    public void setWxNickName(String wxNickName){
        this.wxNickName = wxNickName;
    }
    public String getWxNickName(){
        return this.wxNickName;
    }
    public void setGender(int gender){
        this.gender = gender;
    }
    public int getGender(){
        return this.gender;
    }
    public void setAvatar(String avatar){
        this.avatar = avatar;
    }
    public String getAvatar(){
        return this.avatar;
    }
    public void setIsBandPhone(boolean isBandPhone){
        this.isBandPhone = isBandPhone;
    }
    public boolean getIsBandPhone(){
        return this.isBandPhone;
    }
    public void setIsBandWx(boolean isBandWx){
        this.isBandWx = isBandWx;
    }
    public boolean getIsBandWx(){
        return this.isBandWx;
    }
    public void setIsBandCseeAccount(boolean isBandCseeAccount){
        this.isBandCseeAccount = isBandCseeAccount;
    }
    public boolean getIsBandCseeAccount(){
        return this.isBandCseeAccount;
    }
    public void setGesturePassword(String gesturePassword){
        this.gesturePassword = gesturePassword;
    }
    public String getGesturePassword(){
        return this.gesturePassword;
    }
    public void setBandPhone(String bandPhone){
        this.bandPhone = bandPhone;
    }
    public String getBandPhone(){
        return this.bandPhone;
    }
    public void setRegionalName(String regionalName){
        this.regionalName = regionalName;
    }
    public String getRegionalName(){
        return this.regionalName;
    }
    public void setAreaName(String areaName){
        this.areaName = areaName;
    }
    public String getAreaName(){
        return this.areaName;
    }
    public void setSchoolName(String schoolName){
        this.schoolName = schoolName;
    }
    public String getSchoolName(){
        return this.schoolName;
    }
    public void setClassroomName(String classroomName){
        this.classroomName = classroomName;
    }
    public String getClassroomName(){
        return this.classroomName;
    }
    public void setRegionalAuthority(int regionalAuthority){
        this.regionalAuthority = regionalAuthority;
    }
    public int getRegionalAuthority(){
        return this.regionalAuthority;
    }
    public void setPrvId(int prvId){
        this.prvId = prvId;
    }
    public int getPrvId(){
        return this.prvId;
    }
    public void setCityId(int cityId){
        this.cityId = cityId;
    }
    public int getCityId(){
        return this.cityId;
    }
    public void setCountyId(int countyId){
        this.countyId = countyId;
    }
    public int getCountyId(){
        return this.countyId;
    }
    public void setSchoolId(int schoolId){
        this.schoolId = schoolId;
    }
    public int getSchoolId(){
        return this.schoolId;
    }
    public void setGrade(int grade){
        this.grade = grade;
    }
    public int getGrade(){
        return this.grade;
    }
    public void setClassroomId(int classroomId){
        this.classroomId = classroomId;
    }
    public int getClassroomId(){
        return this.classroomId;
    }
    public void setTownsId(int townsId){
        this.townsId = townsId;
    }
    public int getTownsId(){
        return this.townsId;
    }
    public void setTownsName(String townsName){
        this.townsName = townsName;
    }
    public String getTownsName(){
        return this.townsName;
    }
    public void setIndexUrl(String indexUrl){
        this.indexUrl = indexUrl;
    }
    public String getIndexUrl(){
        return this.indexUrl;
    }
    public void setDiscoveryUrl(String discoveryUrl){
        this.discoveryUrl = discoveryUrl;
    }
    public String getDiscoveryUrl(){
        return this.discoveryUrl;
    }
    public void setSchoolYearData(String schoolYearData){
        this.schoolYearData = schoolYearData;
    }
    public String getSchoolYearData(){
        return this.schoolYearData;
    }
    public void setAccountType(int accountType){
        this.accountType = accountType;
    }
    public int getAccountType(){
        return this.accountType;
    }
    public void setRelationId(String relationId){
        this.relationId = relationId;
    }
    public String getRelationId(){
        return this.relationId;
    }
    public void setRelationUserIds(List<Long> relationUserIds){
        this.relationUserIds = relationUserIds;
    }
    public List<Long> getRelationUserIds(){
        return this.relationUserIds;
    }
    public void setTrueName(String trueName){
        this.trueName = trueName;
    }
    public String getTrueName(){
        return this.trueName;
    }

    @Override
    public String getAuthCacheKey() {
        return this.userUniqueFlag();
    }
}