package mx.com.teclo;

//@Configuration
//@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
public class JndiConfig {

//	// No se excluye DataSourceAutoConfiguration.class para que spring boot levnte las configuraciones automaticas
//	private static final String JNDI_NAME = "jdbc/DATASOURCE";
//
//	@Bean
//	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}
//
//	@Bean
//	@Primary
//	@Resource(name = JNDI_NAME)
//	public DataSource dataSource() {
//
//		JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
//		jndiDataSourceLookup.setResourceRef(true);
//		DataSource dataSource = jndiDataSourceLookup.getDataSource(JNDI_NAME);
//		return dataSource;
//
//	}
//
//	@Bean
//	JdbcTemplate jdbcTemplate(DataSource dataSource) {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
// 		return jdbcTemplate;
//	}
//
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		return new JdbcTemplate(dataSource());
//	}
//
//	@Bean
//	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
//		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//		dataSourceInitializer.setDataSource(dataSource);
//		dataSourceInitializer.setEnabled(true);
// 		return dataSourceInitializer;
//	}
}
