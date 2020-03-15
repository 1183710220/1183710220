public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
		if (sides <= 2 || sideLength <= 0) {
			return;
		}
		for (int i = 1; i <= sides; i++) {
			turtle.forward(sideLength);
			turtle.turn(180 - calculateRegularPolygonAngle(sides));
		}
	}
