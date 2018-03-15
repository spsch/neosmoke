package cz.neoris.smokesuite;

public interface Helper {

    //basic URLs
    final String QA_CEMEXGO_URL = "http://ordersnproduct-qa-2-1.mybluemix.net";
    final String QA_CEMEX_CONSOLE = "http://ordersnproduct-console-dev-2-0-i1.mybluemix.net";
    final String PROD_CEMEXGO_URL = "";
    final String PROD_CEMEX_CONSOLE = "";

    //usernames for cemexgo and QA
    final String QA_CEMEXGO_MX = "customer05cmxgo@gmail.com";
    final String QA_CEMEX_CONSOLE_MX = QA_CEMEXGO_MX;
    final String QA_CEMEXGO_US_RMX = "blazona.da.test@mailinator.com";
    final String QA_CEMEXGO_US_CEM = "ag.da.test@mailinator.com";
    final String QA_CEMEX_CONSOLE_US_RMX = QA_CEMEXGO_US_RMX;
    final String QA_CEMEX_CONSOLE_US_CEM = QA_CEMEXGO_US_CEM;

    //passwords
    final String QA_PWD = "TestCycleV2CemexGo";
    final String PROD_PWD = "";

    //MX details
    final String QA_MX_CUSTOMER_NAME="Home Depot Mexico, S. De R.l. De C.";
    final String QA_MX_CUSTOMER_ID="#0050008828";
    final String QA_MX_ORDERNO="0119815976";


}
