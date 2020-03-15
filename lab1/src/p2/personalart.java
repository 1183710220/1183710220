    public static void drawPersonalArt(Turtle turtle) {
        for (int i = 0 ; i < 1500; i++) {
            turtle.forward(i/2);
            switch ((i/15) % 10) {
                case 0:turtle.color(PenColor.BLACK);break;
                case 1:turtle.color(PenColor.GRAY);break;
                case 2:turtle.color(PenColor.RED);break;
                case 3:turtle.color(PenColor.PINK);break;
                case 4:turtle.color(PenColor.ORANGE);break;
                case 5:turtle.color(PenColor.YELLOW);break;
                case 6:turtle.color(PenColor.GREEN);break;
                case 7:turtle.color(PenColor.CYAN);break;
                case 8:turtle.color(PenColor.BLUE);break;
                case 9:turtle.color(PenColor.MAGENTA);break;
            }
            turtle.turn(91);
        }
