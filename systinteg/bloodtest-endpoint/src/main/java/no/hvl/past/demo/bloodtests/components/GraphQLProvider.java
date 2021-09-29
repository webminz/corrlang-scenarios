package no.hvl.past.demo.bloodtests.components;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.language.StringValue;
import graphql.schema.*;
import graphql.schema.idl.*;
import no.hvl.past.demo.bloodtests.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class GraphQLProvider {


    @Autowired
    private QueryService queryService;

    public static final GraphQLScalarType DATE_TIME = new GraphQLScalarType("DateTime", "Represents a date", new Coercing() {
        @Override
        public Object serialize(Object o) throws CoercingSerializeException {
            if (o instanceof LocalDateTime) {
                LocalDateTime dt = (LocalDateTime) o;
                return dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
            throw new CoercingSerializeException("Unable to serialize object: " + o.toString());
        }

        @Override
        public Object parseValue(Object o) throws CoercingParseValueException {
            if (o instanceof String) {
                String s = o.toString();
                return LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
            throw new CoercingParseValueException("Unable to parse given input: " + o.toString());
        }

        @Override
        public Object parseLiteral(Object o) throws CoercingParseLiteralException {
            if (o instanceof StringValue) {
                String value = ((StringValue) o).getValue();
                return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
            throw new CoercingParseLiteralException("Unable to parse given input: " + o.toString());
        }
    });


    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL resource = Resources.getResource("schema.graphql");
        String sdl = Resources.toString(resource, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring wiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, wiring);
    }


    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()

                .type(TypeRuntimeWiring.newTypeWiring("Query")
                        .dataFetcher("all", dataFetchingEnvironment -> queryService.all())
                        .dataFetcher("forPatient", dataFetchingEnvironment -> queryService.forPatient(dataFetchingEnvironment.getArgument("patient")))
                        .dataFetcher("forPhysician", dataFetchingEnvironment -> queryService.forPhysician(dataFetchingEnvironment.getArgument("physician")))
                )
                .scalar(DATE_TIME)
                .build();
    }


}
