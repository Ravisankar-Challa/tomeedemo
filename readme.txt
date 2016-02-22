Tomee maven plugin looks for tomee conf folder by default in ${project.basedir}/src/main/tomee/conf
This can we can be overridden by tomee maven plugin configuration
<plugin>
	<groupId>org.apache.tomee.maven</groupId>
	<artifactId>tomee-maven-plugin</artifactId>
	<version>7.0.0-M1</version>
	<configuration>
		<config>${project.basedir}/src/main/java/tomee/conf</config>
	</configuration>
</plugin>
In conf folder we can place system.properties, tomee.xml etc
System properties can include data source details
ex:- Data Source 
	 testDataSource = new://Resource?type=javax.sql.DataSource
	 testDataSource.jdbcDriver = org.h2.Driver
	 testDataSource.jdbcUrl = jdbc:h2:tcp://localhost/~/test
	 testDataSource.userName = sa
	 testDataSource.password = sa
	 or encrypted password
	 testDataSource.password = CVTxCNqevHM=
	 testDataSource.passwordCipher = Static3DES
	 
	 JMS Queue 
	 myQueue = new://Resource?type=javax.jms.Queue	 
	 
We can also configure the same in tomee.xml
ex:- Data Source 
	 <?xml version="1.0" encoding="UTF-8"?>
	 <tomee>
	 	<Resource id="TestDataSource" type="DataSource">
		    JdbcDriver  oracle.jdbc.OracleDriver
		    JdbcUrl     jdbc:oracle:thin:@localhost:1521:XE
		    UserName    scott
		    Password    MU1BTzgpsTo= //tiger
			PasswordCipher Static3DES
  		</Resource>	 
	 </tomee>
The above configuration can also be placed in WEB-INF/resources.xml	 
For more details refer - http://tomee.apache.org/datasource-config.html	 
To create a database password cipher run /bin/tomee.bat cipher 'password'

We can also pass these parameters in conf/system.properties file or from command line.
ex:- from command line
For linix only : mvn -DadditionalSystemProperties=-Dpropertyname=value
Linux : mvn clean package tomee:run -DadditionalSystemProperties=-DtestDataSource=new://Resource?type=javax.sql.DataSource -DtestDataSource.jdbcDriver=org.h2.Driver -DtestDataSource.jdbcUrl=jdbc:h2:tcp://localhost/~/test1 -DtestDataSource.userName=sa -DtestDataSource.password=sa
Windows : mvn clean package tomee:run -DtestDataSource=new://Resource?type=javax.sql.DataSource -DtestDataSource.jdbcDriver=org.h2.Driver -DtestDataSource.jdbcUrl=jdbc:h2:tcp://localhost/~/test1 -DtestDataSource.userName=sa -DtestDataSource.password=sa
Sabot:
The Properties themselves can come from java.lang.System.getProperties() using -D[property name]=[property value] on the command line, properties added to a WEB-INF/classes/base.properties file or (optionally for TomEE users) in the conf/system.properties file of your server.
There are various ways of customizing the configuration for differing environments. You can:
    Add a 'test.properties' file to your test resources to override values found in the 'base.properties' file.
    Add additional property file names via the System property 'org.tomitribe.sabot.environment' (Constant ConfigurationResolver.ENVIRONMENT)
    -Dorg.tomitribe.sabot.environment=prod,dev - Sabot would then load the 'prod.properties' and 'dev.properties' files in that order.
ex:- mvn clean package tomee:run -Dorg.tomitribe.sabot.environment=dev
    
H2 Database:
In Mem:
jdbc:h2:mem:test;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
File:
jdbc:h2:~/test1;MODE=Oracle;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE
Server:
jdbc:h2:tcp://localhost/~/test1;MODE=Oracle;DB_CLOSE_ON_EXIT=FALSE:AUTO_RECONNECT=TRUE 

H2 Database:
CREATE SEQUENCE MEM_SEQ_GEN START WITH 1 INCREMENT BY 1
CREATE TABLE Member (id BIGINT NOT NULL, name VARCHAR(255), PRIMARY KEY (id))
DROP SEQUENCE MEM_SEQ_GEN
DROP TABLE Member

Oracle Database:
CREATE SEQUENCE MEM_SEQ_GEN START WITH 1 INCREMENT BY 1
CREATE TABLE Member (id NUMBER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))
DROP SEQUENCE MEM_SEQ_GEN
DROP TABLE Member
