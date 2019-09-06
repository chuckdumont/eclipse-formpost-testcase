package org.eclipse.jetty.formpost.testcase;
import java.util.Hashtable;

import javax.servlet.Servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration<?> registration;

	@Override
	public void start(BundleContext context) throws Exception {
		Hashtable<String, Object> props = new Hashtable<String, Object>();
		props.put("osgi.http.whiteboard.resource.pattern", "/*");
		props.put("osgi.http.whiteboard.resource.prefix", "/WebContent");
		props.put("osgi.http.whiteboard.servlet.pattern", "/MyServlet");
		registration = context.registerService(Servlet.class.getName(), new MyServlet(), props);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		registration.unregister();
	}

}
