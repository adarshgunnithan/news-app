import { AppPage } from './app.po';
import { browser,by,element } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('Loading News Application', () => {
    page.navigateTo();
  });

  it('should be redirect to /login route on opening the application',()=>{

        expect(browser.getCurrentUrl()).toContain('/login')
  });

  it('should be redirect to /register route',()=>{
    browser.element(by.css('.register-button')).click()
    expect(browser.getCurrentUrl()).toContain('/register')
  });

  it('should be able to register admin',()=>{
    browser.element(by.id('firstName')).sendKeys('admin')
    browser.element(by.id('lastName')).sendKeys('admin')
    browser.element(by.id('userid')).sendKeys('admin')
    browser.element(by.id('password')).sendKeys('admin')
    browser.element(by.css('.register-user')).click()
    browser.sleep(20000);
    expect(browser.getCurrentUrl()).toContain('/login')
  });
  it('admin should be able to login and navigate to all news',()=>{
    
    browser.element(by.id('userid')).sendKeys('admin')
    browser.element(by.id('password')).sendKeys('admin')
    browser.element(by.css('.login-user')).click()
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/news/allnews')
  });

  it('admin be able to add movies to application',()=>{
    browser.driver.manage().window().maximize()
    browser.sleep(2000);
    //browser.element(by.css('.movie-thumbnail')).click();
   // browser.sleep(1000);
    browser.element(by.css('.addButton')).click();
   
 
  });


  it('admin be able to navigate to app news',()=>{
  
    browser.sleep(6000);
    browser.element(by.css('.appnews')).click()
    browser.sleep(6000);
    expect(browser.getCurrentUrl()).toContain('appnews')
 
  });
  it('should be able to view details',()=>{
  
    browser.element(by.css('.viewdetail')).click()
    browser.element(by.css('.close')).click()
    expect(browser.getCurrentUrl()).toContain('appnews')
 
  });
  it('should be able to logout',()=>{
    browser.driver.manage().window().maximize()
    browser.sleep(2000);
  
    browser.element(by.css('.logout')).click()
   
    expect(browser.getCurrentUrl()).toContain('login')
 
  });
  it('should be redirect to /register route',()=>{
    browser.element(by.css('.register-button')).click()
    expect(browser.getCurrentUrl()).toContain('/register')
  });

  it('should be able to register normal user',()=>{
    browser.element(by.id('firstName')).sendKeys('adarsh')
    browser.element(by.id('lastName')).sendKeys('adarsh')
    browser.element(by.id('userid')).sendKeys('adarsh')
    browser.element(by.id('password')).sendKeys('adarsh')
    browser.element(by.css('.register-user')).click()
    browser.sleep(10000);
    expect(browser.getCurrentUrl()).toContain('/login')
  });
  it('normal user should be able to login and navigate to all news added by admin',()=>{
    
    browser.element(by.id('userid')).sendKeys('adarsh')
    browser.element(by.id('password')).sendKeys('adarsh')
    browser.element(by.css('.login-user')).click()
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/news/appnews')
  });
  it('user should be able to add movies to watchlist',()=>{
    browser.driver.manage().window().maximize()
    browser.sleep(2000);
    //browser.element(by.css('.movie-thumbnail')).click();
   // browser.sleep(1000);
    browser.element(by.css('.addButton')).click();
    browser.sleep(2000);
    browser.element(by.css('.watchlist')).click();
    browser.sleep(5000);
    expect(browser.getCurrentUrl()).toContain('watchlist')
   
 
  });
  it('user should be able to delete movies from watchlist',()=>{
    browser.driver.manage().window().maximize()
    browser.sleep(2000);
    //browser.element(by.css('.movie-thumbnail')).click();
   // browser.sleep(1000);
    browser.element(by.css('.deleteButton')).click();
    browser.sleep(2000);
    const searchItems=element.all(by.css('.movie-thumbnail'));
    expect(searchItems.count()).toBe(0)
   
 
  });


});
