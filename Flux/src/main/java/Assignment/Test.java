package Assignment;

import CourseUtil.Util;

public class Test {
	public static void main(String[] args) {
		OrderService orderService = new OrderService();
		InventoryService inventoryService = new InventoryService();
		RevenueService revenueService = new RevenueService();
		
		// revenue and inv - observe the order stream
		orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
		orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());
		
		inventoryService.inventoryStream().subscribe(Util.subscriber("inventory"));
		
		revenueService.revenueStream().subscribe(Util.subscriber("revenue"));
		
		Util.sleepSecond(60);
	}
}
