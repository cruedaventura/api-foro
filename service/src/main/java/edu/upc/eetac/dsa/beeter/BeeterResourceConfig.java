package edu.upc.eetac.dsa.beeter;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeeterResourceConfig extends ResourceConfig
{
    public BeeterResourceConfig() {
        packages("edu.upc.eetac.dsa.beeter");
        packages("edu.upc.eetac.dsa.beeter.auth");
        register(RolesAllowedDynamicFeature.class);
    }
}
