package game;


import client.ClientNetwork;
import player.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatGameInfo.
 */
public class ChatGameInfo {

	/**
	 * Send atk message.
	 *
	 * @param atk the atk
	 * @param def the def
	 * @param atkUnit the atk unit
	 * @param defUnit the def unit
	 * @return the string
	 */
	public static String sendAtkMessage(String atk, String def, Card atkUnit, Card defUnit)
	{
		String message = "[GM]: "+atk +"'s "+ atkUnit.getName() +" did "+
				atkUnit.getAttack()+" damage to " + def +
				"'s " + defUnit.getName()+"\n";	
		return message;
		//ClientNetwork.sendChatMessage(ClientNetwork.game.getGame().getGameID(), "ingame", "GM", message);
	}
	
	/**
	 * Send kill message.
	 *
	 * @param atk the atk
	 * @param def the def
	 * @param atkUnit the atk unit
	 * @return the string
	 */
	public static String sendKillMessage(String atk, String def, Card atkUnit)
	{
		String message = "[GM]: "+atk +"'s "+ atkUnit.getName() +" kills "+
				def +"'s unit\n";
		return message;
		//ClientNetwork.sendChatMessage(ClientNetwork.game.getGame().getGameID(), "ingame", "GM", message);
	}
	
	/**
	 * Send trap message.
	 *
	 * @param atk the atk
	 * @param def the def
	 * @param atkUnit the atk unit
	 * @param trap the trap
	 * @return the string
	 */
	public static String sendTrapMessage(String atk,String def,Card atkUnit, Card trap)
	{
		String message = "[GM]: "+ atk+"'s "+ atkUnit.getName() +" takes "+
				trap.getAttack()+ " damage from " + def +"'s "+ trap.getName()+ "\n";
		return message;
		//ClientNetwork.sendChatMessage(ClientNetwork.game.getGame().getGameID(), "ingame", "GM", message);
	}
	
	/**
	 * Send ele message.
	 *
	 * @param atk the atk
	 * @param def the def
	 * @param atkUnit the atk unit
	 * @return the string
	 */
	public static String sendEleMessage(String atk, String def, Card atkUnit)
	{
		String message = "[GM]: "+ atk+"'s "+ atkUnit.getName() +" takes "+
				def+"'s Capitol and eliminates " +def+ "\n";
		return message;
		//ClientNetwork.sendChatMessage(ClientNetwork.game.getGame().getGameID(), "ingame", "GM", message);
	}
	
}
