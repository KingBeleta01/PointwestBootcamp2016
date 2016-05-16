package localConstants;

import org.openqa.selenium.By;

public class amazonConstants {
	
	public String primeMembershipMovie_id = "//*[@id='btfContent']/div[1]/div/div[2]/div/ul";
	public String pmmScrollbar_xpath = "//*[@id='btfContent']/div[1]/div/div[2]";
	public String scrollbar_xpath = "//*[@id='btfContent']/div[1]/div/div[2]/span/span";
	public String movieDivs_id = "//*[@id='btfContent']/div[1]/div/div[2]/div/ul/li";
	public String quickLookButton_xpath = "//*[@id='gw-quick-look-btn']/span/submit";
	public String quickLookPopUp_xpath="//*[@class='imgblock']/img";
	public static By closeQuickLookPopUp_id = By.id("gw-popover-close");
	
	
	public String searchHeader_xpath = "//*[@class='nav-search-field']/input";
	public String searchIcon_xpath = "//*[@id='nav-search']/form/div[2]/div/input";
	public String searchQuery = "selenium";
	public String searchedItemName = "selenium book";
	public String sorting_xpath = "//*[@id='searchSortForm']/select";
	public String firstSearchResult_xpath = "//*[@id='s-results-list-atf']/li[1]/div/div[2]/div/a";
	public String searchResultSuggestions_xpath = "//*[@id='nav-flyout-searchAjax']/div/div[2]/div";
	
}
