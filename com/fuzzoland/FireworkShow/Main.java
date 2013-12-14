// Such compact, much mess, made for le shits and giggles
package com.fuzzoland.FireworkShow;
public class Main extends org.bukkit.plugin.java.JavaPlugin{
	final java.util.HashMap<Long, Long> shows = new java.util.HashMap<Long, Long>();
	final java.util.List<org.bukkit.FireworkEffect.Type> types = new java.util.ArrayList<org.bukkit.FireworkEffect.Type>(java.util.Arrays.asList(org.bukkit.FireworkEffect.Type.BALL, org.bukkit.FireworkEffect.Type.BALL_LARGE, org.bukkit.FireworkEffect.Type.BURST, org.bukkit.FireworkEffect.Type.STAR, org.bukkit.FireworkEffect.Type.CREEPER));
	final java.util.List<org.bukkit.Color> colours = new java.util.ArrayList<org.bukkit.Color>(java.util.Arrays.asList(org.bukkit.Color.AQUA, org.bukkit.Color.BLACK, org.bukkit.Color.BLUE, org.bukkit.Color.FUCHSIA, org.bukkit.Color.GRAY, org.bukkit.Color.GREEN, org.bukkit.Color.LIME, org.bukkit.Color.MAROON, org.bukkit.Color.NAVY, org.bukkit.Color.OLIVE, org.bukkit.Color.ORANGE, org.bukkit.Color.PURPLE, org.bukkit.Color.RED, org.bukkit.Color.SILVER, org.bukkit.Color.TEAL, org.bukkit.Color.WHITE, org.bukkit.Color.YELLOW));
	public boolean onCommand(final org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, final String[] args){
		if(sender.hasPermission("FireworkShow.use") && sender instanceof org.bukkit.entity.Player && (commandLabel.equalsIgnoreCase("FS") || commandLabel.equalsIgnoreCase("FireworkShow")) && args.length == 3){
		    final Long id = new java.util.Random().nextLong();
		    final org.bukkit.Location loc = ((org.bukkit.entity.Player) sender).getLocation();
		    shows.put(id, System.currentTimeMillis());
		    new org.bukkit.scheduler.BukkitRunnable(){
		        public void run(){
		            for(int i = -2; i < 3; i++){
		                org.bukkit.entity.Firework firework = ((org.bukkit.entity.Player) sender).getWorld().spawn(new org.bukkit.Location(loc.getWorld(), loc.getX() + (i * 5), loc.getY(), loc.getZ()), org.bukkit.entity.Firework.class);
		                org.bukkit.inventory.meta.FireworkMeta data = firework.getFireworkMeta();
		                data.addEffects(org.bukkit.FireworkEffect.builder().withColor(colours.get(new java.util.Random().nextInt(17))).withColor(colours.get(new java.util.Random().nextInt(17))).withColor(colours.get(new java.util.Random().nextInt(17))).with(types.get(new java.util.Random().nextInt(5))).trail(new java.util.Random().nextBoolean()).flicker(new java.util.Random().nextBoolean()).build());
		                data.setPower(new java.util.Random().nextInt(2) + 2);
		                firework.setFireworkMeta(data);
		            }
		            if((System.currentTimeMillis() - shows.get(id) > (Double.parseDouble(args[0]) * 1000))){
		                shows.remove(id);
		                cancel();
		            }
		        }
		    }.runTaskTimer(this, 0, Integer.parseInt(args[1]) / 50);
		}
		return true;
	}
}