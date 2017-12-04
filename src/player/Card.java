package player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class Card.
 */
public class Card implements Serializable{
	
	/** The totalcards. */
	private static int totalcards = 19;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1;
	
	/** The name. */
	private String name;
	
	/** The id. */
	private String id;
	
	/** The filename. */
	private String filename;
	
	/** The description. */
	private String description;
	
	/** The rarity. */
	private String rarity;
	
	/** The date created. */
	private String dateCreated;
	
	/** The modified. */
	private String modified;
	
	/** The ability. */
	private String ability;
	
	/** The value. */
	private String value;
	
	/** The type. */
	private String type;
	
	/** The ownedby. */
	private String ownedby;
	
	/** The attack. */
	private int attack;
	
	/** The speed. */
	private int speed;
	
	/** The health. */
	private int health;
	
	/** The cost. */
	private int cost;
	
	/** The range. */
	private int range;
	
	/** The max HP. */
	private int maxHP;
	
	/** The move left. */
	private int moveLeft;
	
	/**
	 * Instantiates a new card.
	 *
	 * @param name the name
	 * @param id the id
	 * @param filename the filename
	 * @param description the description
	 * @param rarity the rarity
	 * @param dateCreated the date created
	 * @param modified the modified
	 * @param ability the ability
	 * @param value the value
	 * @param type the type
	 * @param attack the attack
	 * @param speed the speed
	 * @param health the health
	 * @param cost the cost
	 * @param range the range
	 * @param ownedby the ownedby
	 */
	public Card(String name, String id, String filename, String description, String rarity, String dateCreated,
			String modified, String ability, String value, String type, int attack, int speed, int health, int cost, int range,
			String ownedby) {

		this.name = name;
		this.id = id;
		this.filename = filename;
		this.description = description;
		this.rarity = rarity;
		this.dateCreated = dateCreated;
		this.modified = modified;
		this.ability = ability;
		this.value = value;
		this.type = type;
		this.attack = attack;
		this.speed = speed;
		this.health = health;
		this.cost = cost;
		this.range = range;
		this.ownedby = ownedby;
		this.maxHP = health;
		
		this.moveLeft = speed;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename.
	 *
	 * @param filename the new filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the attack.
	 *
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Sets the attack.
	 *
	 * @param attack the new attack
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the health.
	 *
	 * @param health the new health
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Deal damage.
	 *
	 * @param amount the amount
	 * @return true, if successful
	 */
	public boolean dealDamage(int amount) {
		this.health -= amount;
		return (health>0);
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the rarity.
	 *
	 * @return the rarity
	 */
	public String getRarity() {
		return rarity;
	}

	/**
	 * Sets the rarity.
	 *
	 * @param rarity the new rarity
	 */
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	/**
	 * Gets the date created.
	 *
	 * @return the date created
	 */
	public String getDateCreated() {
		return dateCreated;
	}

	/**
	 * Sets the date created.
	 *
	 * @param dateCreated the new date created
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	public String getModified() {
		return modified;
	}

	/**
	 * Sets the modified.
	 *
	 * @param modified the new modified
	 */
	public void setModified(String modified) {
		this.modified = modified;
	}

	/**
	 * Gets the ability.
	 *
	 * @return the ability
	 */
	public String getAbility() {
		return ability;
	}

	/**
	 * Sets the ability.
	 *
	 * @param ability the new ability
	 */
	public void setAbility(String ability) {
		this.ability = ability;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	/**
	 * Gets the range.
	 *
	 * @return the range
	 */
	public int getRange() {
		return this.range;
	}
	
	/**
	 * Gets the owned by.
	 *
	 * @return the owned by
	 */
	public String getOwnedBy() {
		return this.ownedby;
	}
	
	/**
	 * Sets the ownedby.
	 *
	 * @param ownedby the new ownedby
	 */
	public void setOwnedby(String ownedby) {
		this.ownedby = ownedby;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Card [name=" + name + ", id=" + id + ", filename=" + filename + ", description=" + description
				+ ", rarity=" + rarity + ", dateCreated=" + dateCreated + ", modified=" + modified + ", ability="
				+ ability + ", value=" + value + ", type=" + type + ", attack=" + attack + ", speed=" + speed
				+ ", health=" + health + ", cost=" + cost + "]";
	}
	
	/**
	 * Serialize.
	 *
	 * @return the string
	 */
	public String serialize() {
		return name + "\0" + id + "\0" + filename + "\0" + description + "\0" + rarity + "\0" + dateCreated + "\0" + modified + "\0" + 
	           ability + "\0" + value + "\0" + type + "\0" +  attack + "\0" + speed + "\0" + health + "\0" + cost + "\0" + range + "\0" + ownedby;
	}
	
	/**
	 * Deserialize.
	 *
	 * @param s the s
	 * @return the card
	 */
	public static Card deserialize(String s) {
		ArrayList<String> cardTokens= new ArrayList<String>(Arrays.asList(s.split(Character.toString('\0'))));
		Card c = new Card(
				cardTokens.get(0),
				cardTokens.get(1),
				cardTokens.get(2),
				cardTokens.get(3),
				cardTokens.get(4),
				cardTokens.get(5),
				cardTokens.get(6),
				cardTokens.get(7),
				cardTokens.get(8),
				cardTokens.get(9),
				Integer.parseInt(cardTokens.get(10)),
				Integer.parseInt(cardTokens.get(11)),
				Integer.parseInt(cardTokens.get(12)),
				Integer.parseInt(cardTokens.get(13)),
				Integer.parseInt(cardTokens.get(14)),
				cardTokens.get(15)
				);
		
		return c;
	}

/**
 * Clone.
 *
 * @author Jeremiah Brusegaard
 * Clones object
 * @return the object
 */
	public Object clone() {
		Object clonedObj = null;
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(this);
			objectOutputStream.flush();
			objectOutputStream.close();
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
			clonedObj = ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return clonedObj;
	}
	
	/**
	 * Gets the total cards in game.
	 *
	 * @return the total cards in game
	 */
	public static int getTotalCardsInGame() {
		return totalcards;
	}
	
	/**
	 * Gets the max HP.
	 *
	 * @return the max HP
	 */
	public int getMaxHP() {
		return this.maxHP;
	}
	
	/**
	 * Gets the move left.
	 *
	 * @return the move left
	 */
	public int getMoveLeft() {
		return moveLeft;
	}

	/**
	 * Reset speed.
	 */
	public void resetSpeed() {
		moveLeft = speed;
	}
	
	/**
	 * Move.
	 *
	 * @param distance the distance
	 */
	public void move(int distance) {
		moveLeft -= distance;
	}
	
	/**
	 * Can move.
	 *
	 * @return true, if successful
	 */
	public boolean canMove() {
		return(moveLeft>0);
	}
	
	/**
	 * End move.
	 */
	public void endMove() {
		moveLeft = 0;
	}
}
