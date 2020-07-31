package buildlogic.libraries

abstract class ExternalLibrariesExtension {
    private
    val jettyBase = "org.eclipse.jetty:jetty"
    private
    val junitBase = "org.junit.jupiter:junit-jupiter"

    val jettyBom = "${jettyBase}-bom"
    val jettyServer = "${jettyBase}-server"
    val jettyServlet = "${jettyBase}-servlet"

    val guava = "com.google.guava:guava"

    val httpclient = "org.apache.httpcomponents:httpclient"

    val junitApi = "${junitBase}-api"
    val junitEngine = "${junitBase}-engine"
}