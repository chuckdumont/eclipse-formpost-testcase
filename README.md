This is a test case for https://bugs.eclipse.org/bugs/show_bug.cgi?id=500783

The test case demonstrates how a servlet implemented with `org.eclipse.equinox.http.jetty` cannot read the input stream of a request with content type `application/x-www-form-urlencoded`.

To reproduce, import as a Maven project into Eclipse, then build with Maven using the `package` goal.

Create a Run Configuration based on the OSGi Framework and add the following to the VM Arguments in the Arguments tab (you can specify whatever port you want to use).

```
-Dorg.osgi.service.http.port=80
```

In the Bundles tab, select the project under Workspaces, and select `Target Platform`.
Click on Run and then enter the following URL into a browser:

http://localhost/index.html

You will see a form with a couple of pre-populated input fields.  Click submit to submit the form to the test servlet.  The response will show the Content-Length of the request as well as the number of bytes that were read from the input stream, which witch will be zero, demonstrating the problem.  The expected result would be that the same number of bytes are read from the input stream as were submitted in the request. 

