Dependency Setup

Add SQLib as a dependency in your pom.xml:

xml

<dependency>
    <groupId>io.github.pigaut.lib.sql</groupId>
    <artifactId>SQLib</artifactId>
    <version>1.0</version>
</dependency>

This dependency allows you to include SQLib functionality in your project.
Shading with Maven Shade Plugin

To avoid conflicts with other libraries using the same package, shade SQLib into your own package namespace using the Maven Shade Plugin.
Step-by-Step Configuration

    Add Maven Shade Plugin to pom.xml:

    Ensure you have the Maven Shade Plugin configured in your pom.xml:

    xml

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.6.0</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <relocations>
                            <relocation>
                                <pattern>io.github.pigaut.lib.sql</pattern>
                                <shadedPattern>YOUR_PACKAGE.shaded.sqlib</shadedPattern>
                            </relocation>
                        </relocations>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

Replace YOUR_PACKAGE with your own package namespace where SQLib will be shaded.
