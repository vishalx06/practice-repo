import java.util.*;

import static javax.swing.text.html.HTML.Tag.SELECT;

/*
* You are given a list of airline tickets where each ticket is represented as:
[from, to] Reconstruct the itinerary such that: The itinerary starts from "JFK"
All tickets are used exactly once If multiple valid itineraries exist,
return the one with the lexicographically smallest order
Return the itinerary as a list of airports

🔢 Example Input
tickets = [
  ["JFK", "SFO"],
  ["JFK", "ATL"],
  ["SFO", "ATL"],
  ["ATL", "JFK"],
  ["ATL", "SFO"]
]

✅ Expected Output
["JFK", "ATL", "JFK", "SFO", "ATL", "SFO"]
* */
public class BharatPayFLightSolution {
    public List<String> findItinerary(List<List<String>>tickets){
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for(List<String> ticket: tickets){
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).offer(to);
        }
        LinkedList<String> result = new LinkedList<>();
        dfs("JFK", graph, result);
        return result;
    }

    private void dfs(String airport, Map<String, PriorityQueue<String>> graph, LinkedList<String> result) {
        PriorityQueue<String> destination = graph.get(airport);
        while(destination != null && !destination.isEmpty()){
            String next = destination.poll();
            dfs(next,graph,result);
        }
        result.addFirst(airport);
    }
//    tickets = [
//            ["JFK", "SFO"],
//            ["JFK", "ATL"],
//            ["SFO", "ATL"],
//            ["ATL", "JFK"],
//            ["ATL", "SFO"]
//            ]
    public static void main(String[] args) {
        BharatPayFLightSolution sol = new BharatPayFLightSolution();
        List<List<String>> tickets = Arrays.asList(
            Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO")
        );

        System.out.println(sol.findItinerary(tickets));
    }
}


//
//        Top 3 products by revenue per category
//
//        Schema:
//        products(id, category_id, name) // p
//        order_items(id, order_id, product_id, price, qty) // oi
//        categories(id, name) // c


//        WITH product_revenue As(
//            SELECT
//                    c.name AS category_name,
//                    p.name AS product_name,
//                    SUM(oi.price * oi.qty) AS revenue
//            FROM product p
//            JOIN category c
//                ON p.category_id = c.id
//            JOIN order_items oi
//                ON oi.product_id = p
//            GROUP BY c.name, p.name
//        ),
//        ranked_product AS (
//                SELECT
//                    category_name,
//                    product_name,
//                    revenue,
//                ROW_NUMBER() (
//                        PARTITION BY category_name
//                        ORDER BY revenue DESC
//                        ) AS rn
//                FROM product_revenue
//            )
//            SELECT
//                category_name,
//                product_name,
//                revenue
//            FROM ranked_products
//            WHERE rn <=3