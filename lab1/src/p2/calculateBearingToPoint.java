public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY, int targetX,
			int targetY) {
		// throw new RuntimeException("implement me!");
		if (currentX == targetX && currentY == targetY)
			return 0.0;
		double degreess = Math.atan2(targetY - currentY, targetX - currentX) / Math.PI * 180.00;
		if (degreess < 0)
			degreess += 360.00;
		currentBearing = 90.00 - currentBearing;
		if (currentBearing < 0)
			currentBearing += 360.00;
		double ans = currentBearing - degreess;
		if (ans < 0)
			ans += 360.00;
		return ans;
	}