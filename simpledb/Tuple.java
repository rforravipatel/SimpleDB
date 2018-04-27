package simpledb;

/**
 * Tuple maintains information about the contents of a tuple. Tuples have a specified schema specified by a TupleDesc
 * object and contain Field objects with the data for each field.
 */
public class Tuple {

	/**
	 * The tuple descriptor associated with this Tuple.
	 */
	protected TupleDesc tupledesc;

	/**
	 * The RecordId of this Tuple.
	 */
	protected RecordId recID;

	/**
	 * The Fields of this Tuple.
	 */
	protected Field fields[];

	/**
	 * Create a new tuple with the specified schema (type).
	 *
	 * @param td
	 *            the schema of this tuple. It must be a valid TupleDesc instance with at least one field.
	 */
	public Tuple(TupleDesc td) {
		if (td.numFields() > 0) {
			tupledesc = td;
			fields = new Field[td.numFields()];
		}
	}

	/**
	 * @return The TupleDesc representing the schema of this tuple.
	 */
	public TupleDesc getTupleDesc() {
		return tupledesc;
	}

	/**
	 * @return The RecordID representing the location of this tuple on disk. May be null.
	 */
	public RecordId getRecordId() {
		return recID;
	}

	/**
	 * Set the RecordID information for this tuple.
	 * 
	 * @param rid
	 *            the new RecordID for this tuple.
	 */
	public void setRecordId(RecordId rid) {
		recID = rid;
	}

	/**
	 * Change the value of the ith field of this tuple.
	 *
	 * @param i
	 *            index of the field to change. It must be a valid index.
	 * @param f
	 *            new value for the field.
	 */
	public void setField(int i, Field f) {
		if (i >= 0 && i < fields.length)
			fields[i] = f;
	}

	/**
	 * @return the value of the ith field, or null if it has not been set.
	 *
	 * @param i
	 *            field index to return. Must be a valid index.
	 */
	public Field getField(int i) {
		if (i >= 0 && i < fields.length)
			return fields[i];
		return null;
	}

	/**
	 * Returns a string representing this Tuple. Note that to pass the system tests, the format needs to be as follows:
	 * "fields[0], fields[1], ..., fields[M-1]", where M is the number of fields.
	 */
	public String toString() throws UnsupportedOperationException{
		// some code goes here
		StringBuilder sb1 = new StringBuilder(); //sb1 stores the entire string
		for(int i=0; i < fields.length;i++)      //loop up to length of fields
		{
			sb1.append(fields[i]);               //concating the all tuples into string
			if(i!=fields.length-1)			     //to check the last element of fields
			{
				sb1.append(", ");                //to separate tuples
			}
		}
		//System.out.println(sb1);
		return sb1.toString();                   
        //throw new UnsupportedOperationException("Implement this");
	}
}
