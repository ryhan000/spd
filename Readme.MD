Briefing of SPDApp2 web application
===================================

(This application is developed for my personal needs)
Main goal - is the accounting of individual-entrepreneurs (agreements, payments, accounts, 
printing documents, payroll calculations). 
 
For example, the firm has some individual-entrepreneurs. We've got agreement with each entrepreneur. Every agreement has got specification and service act.
As result we are able to print the agreement's specification and service act.

Technologies are using:
- Spring (MVC, Data), JPA/Hibernate, Maven, JSP(Bootstrap 4, Javascript), MySQL, JasperReports, Tomcat
 
Future features
===============
* Agreement template printing for entrepreneur
* Agreement pdf files saving into BD (upload, download)
* Localization
* REST WS (done)
* Spring Security (in process...)

Need to do
================
+ add services for all controllers
+ change jsp -> jspx
+ validation
+ add HandlerExceptionResolver
+ add ContentNegotiatingViewResolver
 

