package edu.upc.eetac.dsa.beeter;

import org.glassfish.jersey.server.ResourceConfig;

public class BeeterResourceConfig extends ResourceConfig
{
    public BeeterResourceConfig() {
        packages("edu.upc.eetac.dsa.beeter");
    }
}
