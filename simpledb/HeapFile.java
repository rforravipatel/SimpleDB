package simpledb;

import java.io.*;
import java.util.*;

/**
 * HeapFile is an implementation of a DbFile that stores a collection of tuples in no particular order. Tuples are
 * stored on pages, each of which is a fixed size, and the file is simply a collection of those pages. HeapFile works
 * closely with HeapPage. The format of HeapPages is described in the HeapPage constructor.
 * 
 * @see simpledb.HeapPage#HeapPage
 */
public class HeapFile implements DbFile {

	/**
	 * The File associated with this HeapFile.
	 */
	protected File file;

	/**
	 * The TupleDesc associated with this HeapFile.
	 */
	protected TupleDesc td;

	/**
	 * Constructs a heap file backed by the specified file.
	 * 
	 * @param f
	 *            the file that stores the on-disk backing store for this heap file.
	 */
	public HeapFile(File f, TupleDesc td) {
		this.file = f;
		this.td = td;
	}

	/**
	 * Returns the File backing this HeapFile on disk.
	 * 
	 * @return the File backing this HeapFile on disk.
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Returns an ID uniquely identifying this HeapFile. Implementation note: you will need to generate this tableid
	 * somewhere ensure that each HeapFile has a "unique id," and that you always return the same value for a particular
	 * HeapFile. We suggest hashing the absolute file name of the file underlying the heapfile, i.e.
	 * f.getAbsoluteFile().hashCode().
	 * 
	 * @return an ID uniquely identifying this HeapFile.
	 */
	public int getId() {
		return file.getAbsoluteFile().hashCode();
	}

	/**
	 * Returns the TupleDesc of the table stored in this DbFile.
	 * 
	 * @return TupleDesc of this DbFile.
	 */
	public TupleDesc getTupleDesc() {
		return td;
	}

	// see DbFile.java for javadocs
	public Page readPage(PageId pid) {
		// some code goes here
		RandomAccessFile file;
		
		try{
			file = new RandomAccessFile(getFile(), "rw");        //getting file
			file.seek(BufferPool.PAGE_SIZE*pid.pageno());		 //setting offset
			byte[] d = new byte[BufferPool.PAGE_SIZE];           //d array to store data of page size
			file.read(d);										 //reading file
			file.close();										 //closing file
			HeapPage h = new HeapPage((HeapPageId)pid, d);       //creating a HeapPage and storing data
			return h;											 //returning created heapPage
		}catch(IOException e)
		{
			e.getStackTrace();
		}
		throw new UnsupportedOperationException("Implement this");
	}

	// see DbFile.java for javadocs
	public void writePage(Page page) throws IOException {
		// some code goes here
		// not necessary for assignment1
		throw new UnsupportedOperationException("Implement this");
	}

	/**
	 * Returns the number of pages in this HeapFile.
	 */
	public int numPages() {
		return (int) (file.length() / BufferPool.PAGE_SIZE);
	}

	// see DbFile.java for javadocs
	public ArrayList<Page> addTuple(TransactionId tid, Tuple t)
			throws DbException, IOException, TransactionAbortedException {
		// some code goes here
		// not necessary for assignment1
		throw new UnsupportedOperationException("Implement this");
	}

	// see DbFile.java for javadocs
	public Page deleteTuple(TransactionId tid, Tuple t) throws DbException, TransactionAbortedException {
		// some code goes here
		// not necessary for assignment1
		throw new UnsupportedOperationException("Implement this");
	}

	// see DbFile.java for javadocs
	public DbFileIterator iterator(TransactionId tid) {

		return new DbFileIterator() {

			/**
			 * The ID of the page to read next.
			 */
			int nextPageID = 0;

			/**
			 * An iterator over all tuples from the page read most recently.
			 */
			Iterator<Tuple> iterator = null;

			@Override
			public void open() throws DbException, TransactionAbortedException {
				nextPageID = 0;
				// obtains an iterator over all tuples from page 0
				iterator = ((HeapPage) Database.getBufferPool().getPage(tid, new HeapPageId(getId(), nextPageID++),
						Permissions.READ_WRITE)).iterator();
			}

			@Override
			public boolean hasNext() throws DbException, TransactionAbortedException {
				// some code goes here
				
				throw new UnsupportedOperationException("Implement this");

			}

			@Override
			public Tuple next() throws DbException, TransactionAbortedException, NoSuchElementException {
				// some code goes here
				
					return iterator.next();
				
				//throw new UnsupportedOperationException("Implement this");
			}

			@Override
			public void rewind() throws DbException, TransactionAbortedException {
				close();
				open();
			}

			@Override
			public void close() {
				iterator = null;
			}

		};
	}

}
