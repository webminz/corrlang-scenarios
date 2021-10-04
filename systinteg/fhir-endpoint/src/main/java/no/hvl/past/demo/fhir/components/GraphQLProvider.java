package no.hvl.past.demo.fhir.components;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.TypeResolutionEnvironment;
import graphql.language.StringValue;
import graphql.schema.*;
import graphql.schema.idl.*;
import no.hvl.past.demo.fhir.entities.*;
import no.hvl.past.demo.fhir.service.MutationService;
import no.hvl.past.demo.fhir.service.QueryService;
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
    private MutationService mutationService;

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


    public static final GraphQLScalarType DATE = new GraphQLScalarType("Date", "Represents a date", new Coercing() {
        @Override
        public Object serialize(Object o) throws CoercingSerializeException {
            if (o instanceof LocalDate) {
                LocalDate dt = (LocalDate) o;
                return dt.format(DateTimeFormatter.ISO_LOCAL_DATE);
            }
            throw new CoercingSerializeException("Unable to serialize object: " + o.toString());
        }

        @Override
        public Object parseValue(Object o) throws CoercingParseValueException {
            if (o instanceof String) {
                String s = o.toString();
                return LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
            }
            throw new CoercingParseValueException("Unable to parse given input: " + o.toString());
        }

        @Override
        public Object parseLiteral(Object o) throws CoercingParseLiteralException {
            if (o instanceof StringValue) {
                String value = ((StringValue) o).getValue();
                return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
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
                        .dataFetcher("patients", dataFetchingEnvironment -> queryService.patients())
                        .dataFetcher("patient", dataFetchingEnvironment -> queryService.patient(dataFetchingEnvironment.getArgument("id")))
                        .dataFetcher("observations", dataFetchingEnvironment -> queryService.observations())
                        .dataFetcher("observation", dataFetchingEnvironment -> queryService.observation(dataFetchingEnvironment.getArgument("id")))
                        .dataFetcher("diagnosticReports", dataFetchingEnvironment -> queryService.diagnosticReports())
                        .dataFetcher("diagnosticReport", dataFetchingEnvironment -> queryService.diagnosticReport(dataFetchingEnvironment.getArgument("id")))
                        .dataFetcher("encounters", dataFetchingEnvironment -> queryService.encounters())
                        .dataFetcher("encounter", dataFetchingEnvironment -> queryService.encounter(dataFetchingEnvironment.getArgument("id")))
                )
                .type(TypeRuntimeWiring.newTypeWiring("Mutation")
                        .dataFetcher("insertPatient", dataFetchingEnvironment -> mutationService.insertPatient(
                                dataFetchingEnvironment.getArgument("identifier"),
                                dataFetchingEnvironment.getArgument("identifierSystem"),
                                Patient.AdministrativeGender.valueOf(dataFetchingEnvironment.getArgument("gender")),
                                dataFetchingEnvironment.getArgument("familyName"),
                                dataFetchingEnvironment.getArgument("birthdate"),
                                dataFetchingEnvironment.getArgument("givenNames")
                        ))
                        .dataFetcher("addAddressForPatient", dataFetchingEnvironment -> mutationService.addAddressForPatient(
                                Long.parseLong(dataFetchingEnvironment.getArgument("patientId")),
                                Address.AddressUse.valueOf(dataFetchingEnvironment.getArgument("use")),
                                dataFetchingEnvironment.getArgument("country"),
                                dataFetchingEnvironment.getArgument("city"),
                                dataFetchingEnvironment.getArgument("postalCode"),
                                dataFetchingEnvironment.getArgument("street"),
                                dataFetchingEnvironment.getArgument("streetNo")
                        ))
                        .dataFetcher("addObservation", dataFetchingEnvironment -> mutationService.addObservation(
                                Long.parseLong(dataFetchingEnvironment.getArgument("subjectId")),
                                dataFetchingEnvironment.getArgument("diagnosticReportId") == null ? null : Long.parseLong(dataFetchingEnvironment.getArgument("diagnosticReportId")),
                                dataFetchingEnvironment.getArgument("encounterId") == null ? null : Long.parseLong(dataFetchingEnvironment.getArgument("encounterId")),
                                dataFetchingEnvironment.getArgument("effectiveDateTime"),
                                dataFetchingEnvironment.getArgument("quantityValue"),
                                dataFetchingEnvironment.getArgument("quantityUnit"),
                                dataFetchingEnvironment.getArgument("code"),
                                dataFetchingEnvironment.getArgument("codeSystem")
                        ))
                        .dataFetcher("addDiagnosticReport", dataFetchingEnvironment -> mutationService.addDiagnosticReport(
                                Long.parseLong(dataFetchingEnvironment.getArgument("subjectId")),
                                dataFetchingEnvironment.getArgument("encounterId") == null ? null : Long.parseLong(dataFetchingEnvironment.getArgument("encounterId")),
                                DiagnosticReport.DiagnosticReportStatus.valueOf(dataFetchingEnvironment.getArgument("status")),
                                dataFetchingEnvironment.getArgument("issued"),
                                dataFetchingEnvironment.getArgument("code"),
                                dataFetchingEnvironment.getArgument("codeSystem")
                        ))
                        .dataFetcher("addEncounter", dataFetchingEnvironment -> mutationService.addEncounter(
                                Long.parseLong(dataFetchingEnvironment.getArgument("subjectId")),
                                Encounter.EncounterStatus.valueOf(dataFetchingEnvironment.getArgument("status")),
                                dataFetchingEnvironment.getArgument("start")
                        ))
                        .dataFetcher("deletePatient", dataFetchingEnvironment -> mutationService.deletePatient(dataFetchingEnvironment.getArgument("id")))
                        .dataFetcher("deleteObservation", dataFetchingEnvironment -> mutationService.deleteObservation(dataFetchingEnvironment.getArgument("id")))
                        .dataFetcher("deleteEncounter", dataFetchingEnvironment -> mutationService.deleteEncounter(dataFetchingEnvironment.getArgument("encounter")))
                        .dataFetcher("deleteReport", dataFetchingEnvironment -> mutationService.deleteReport(dataFetchingEnvironment.getArgument("id")))
                )
                .scalar(DATE_TIME)
                .scalar(DATE)
                .build();
    }


}
