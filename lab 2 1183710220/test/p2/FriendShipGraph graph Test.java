FriendShipGraph graph = new FriendShipGraph();

           Person rachel = new Person("rachel");

           Person ross = new Person("ross");

           Person ben = new Person("ben");

           Person kramer = new Person("kramer");

           graph.addVertex(rachel);

           graph.addVertex(ross);

           graph.addVertex(ben);

           graph.addVertex(kramer);

           graph.addEdge(rachel, ross);

           graph.addEdge(ross, rachel);

           graph.addEdge(ross, ben);

           graph.addEdge(ben, ross);

           System.out.println(graph.getDistance(rachel,ross));

           System.out.println(graph.getDistance(rachel,ben));

           System.out.println(graph.getDistance(rachel,rachel));

           System.out.println(graph.getDistance(rachel,kramer));