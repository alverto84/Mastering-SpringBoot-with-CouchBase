
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.queries.ConjunctionQuery;
import com.couchbase.client.java.search.queries.DisjunctionQuery;
import com.couchbase.client.java.search.queries.MatchPhraseQuery;
import com.couchbase.client.java.search.queries.MatchQuery;
import com.couchbase.client.java.search.queries.PrefixQuery;
import com.couchbase.client.java.search.queries.RegexpQuery;
import com.couchbase.client.java.search.queries.WildcardQuery;
import com.couchbase.client.java.search.result.SearchQueryResult;
import com.couchbase.client.java.search.result.SearchQueryRow;

public class CouchBaseClient {

	private static void printResult(String label, SearchQueryResult resultObject, Bucket bucket) {
		System.out.println();
		System.out.println("= = = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("= = = = = = = = = = = = = = = = = = = = = = =");
		System.out.println();
		System.out.println(label);
		System.out.println();

		for (SearchQueryRow row : resultObject) {
			String name = bucket.get(row.id()).content().get("name").toString();
			String country = bucket.get(row.id()).content().get("country").toString();
			System.out.println(name + ":" + country);

		}
	}
	
	
	public static void phraseQueryMethod(Bucket bucket) {
		String indexName = "travelbucket-airline";
		MatchPhraseQuery mpq = new MatchPhraseQuery("British Airways").field("name");
		SearchQueryResult result = bucket.query(new SearchQuery(indexName, mpq).limit(10));
		printResult("Phrase Query", result, bucket);
	}

	public static void wildCardQueryMethod(Bucket bucket) {
		String indexName = "travelbucket-airline";
		WildcardQuery query = SearchQuery.wildcard("*british*").field("name");
		SearchQueryResult result = bucket.query(new SearchQuery(indexName, query).limit(10));

		printResult("Wild Card Query", result, bucket);
	}

	public static void prefixQueryMethod(Bucket bucket) {
		String indexName = "travelbucket-airline";
		PrefixQuery query = SearchQuery.prefix("british").field("name");

		SearchQueryResult result = bucket.query(new SearchQuery(indexName, query).limit(10));

		printResult("Prefix Query", result, bucket);
	}
	
	public static void disjunctionQueryMethod(Bucket bucket) {
		String indexName = "travelbucket-airline";
		MatchQuery firstQuery = SearchQuery.match("British way").field("name");
		RegexpQuery secondQuery = SearchQuery.regexp(".*iti.*").field("name");
		DisjunctionQuery conjunctionQuery = SearchQuery.disjuncts(firstQuery, secondQuery);
		SearchQueryResult result = bucket.query(new SearchQuery(indexName, conjunctionQuery).limit(10));
		printResult("Disjunction Query", result, bucket);
	}

	public static void conjunctionQueryMethod(Bucket bucket) {
		String indexName = "travelbucket-airline";
		MatchQuery firstQuery = SearchQuery.match("British way").field("name");

		RegexpQuery secondQuery = SearchQuery.regexp(".*iti.*").field("name");
		ConjunctionQuery conjunctionQuery = SearchQuery.conjuncts(firstQuery, secondQuery);

		SearchQueryResult result = bucket.query(new SearchQuery(indexName, conjunctionQuery).limit(10));

		printResult("Conjunction Query", result, bucket);
	}
	
	public static void regexpQueryMethod(Bucket bucket) {
		String indexName = "travelbucket-airline";
		RegexpQuery query = SearchQuery.regexp(".*iti.*").field("name");

		SearchQueryResult result = bucket.query(new SearchQuery(indexName, query).limit(10));

		printResult("Regexp Query", result, bucket);
	}

	public static void simpleTextQuery(Bucket bucket) {
		String indexName = "travelbucket-airline";
		MatchQuery query = SearchQuery.match("British way").field("name");

		SearchQuery sq = new SearchQuery(indexName, query).limit(10);

		SearchQueryResult result = bucket.query(sq);

		printResult("Simple Text Query", result, bucket);
	}

	public static void main(String[] args) throws Exception {

		CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder().connectTimeout(10000).build();
		Cluster cluster = CouchbaseCluster.create(env, "localhost");
		cluster.authenticate("admin", "password");
		Bucket travelSample = cluster.openBucket("travel-sample");
		//simpleTextQuery(travelSample);
		//regexpQueryMethod(travelSample);
		//conjunctionQueryMethod(travelSample);
		//disjunctionQueryMethod(travelSample);
		
		prefixQueryMethod(travelSample);
		phraseQueryMethod(travelSample);
		wildCardQueryMethod(travelSample);
		cluster.disconnect();

	}

}