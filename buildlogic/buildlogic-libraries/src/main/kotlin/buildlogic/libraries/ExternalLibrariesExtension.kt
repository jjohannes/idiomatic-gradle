package buildlogic.libraries

abstract class ExternalLibrariesExtension {
    private
    val jettyBase = "org.eclipse.jetty:jetty"

    val jettyBom = "${jettyBase}-bom"
    val jettyServer = "${jettyBase}-server"
    val jettyServlet = "${jettyBase}-servlet"

    val guava = "com.google.guava:guava"

    val httpclient = "org.apache.httpcomponents:httpclient"
}