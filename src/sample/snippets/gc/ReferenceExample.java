package sample.snippets.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceExample {

	private static final String BORDER = "---------------------------------------";
	private static final String GC_CALLED = "called System.gc()";
	private static final String REFERENCE_CLEARED = "cleared references by 'null' assignment";
	private static final String REASSIGN_REFERENT = "Reassign referent";

	public static void main(String[] args) {
		try {
			log("\n######## start-showSoftReferenceExample #######");
			showSoftReferenceExample();
			log("\n########  end-showSoftReferenceExample #######");

			log("\n######## start-showWeakReferenceExample #######");

			showWeakReferenceExample();

			log("\n########  end-showWeakReferenceExample ######## ");

			log("\n######## start-showPhantomReferenceExample #######");
			showPhantomReferenceExample();
			log("\n########  end-showPhantomReferenceExample #######");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void showSoftReferenceExample() {
		Person alice = new Person();

		ReferenceQueue<Person> softRefQueue = new ReferenceQueue<>();
		SoftReference<Person> softPersonRef = new SoftReference<>(alice, softRefQueue);

		Person softPerson = softPersonRef.get();

		log(BORDER);
		log("alice: " + alice);
		log("softPerson: " + softPerson);
		log(BORDER);

		alice = null;
		softPerson = null;
		log(REFERENCE_CLEARED);

		log(BORDER);
		log("alice: " + alice);
		log("softPerson: " + softPerson);
		log(BORDER);

		System.gc();
		log(GC_CALLED);

		log(REASSIGN_REFERENT);
		softPerson = softPersonRef.get();

		log(BORDER);
		log("alice: " + alice);
		log("softPerson: " + softPerson);
		log(BORDER);

		Reference<? extends Person> softPersionRefFromQueue = softRefQueue.poll();

		log(BORDER);
		log("softPersionRefFromQueue: " + softPersionRefFromQueue);
		log(BORDER);
	}

	private static void showWeakReferenceExample() throws InterruptedException {
		Person bob = new Person();

		ReferenceQueue<Person> weakRefQueue = new ReferenceQueue<>();
		WeakReference<Person> weakPersonRef = new WeakReference<>(bob, weakRefQueue);

		Person weakPerson = weakPersonRef.get();

		log(BORDER);
		log("bob: " + bob);
		log("weakPerson: " + weakPerson);
		log(BORDER);

		bob = null;
		weakPerson = null;
		log(REFERENCE_CLEARED);

		log(BORDER);
		log("bob: " + bob);
		log("weakPerson: " + weakPerson);
		log(BORDER);

		System.gc();
		log(GC_CALLED);

		log(REASSIGN_REFERENT);
		weakPerson = weakPersonRef.get();

		log(BORDER);
		log("bob: " + bob);
		log("weakPerson: " + weakPerson);
		log(BORDER);

		Reference<? extends Person> weakPersionRefFromQueue = weakRefQueue.remove();
		Person gcObject = weakPersionRefFromQueue.get();
		log(BORDER);
		log("weakPersionRefFromQueue: " + weakPersionRefFromQueue);
		log("gcObject: " + gcObject);
		log(BORDER);
	}

	private static void showPhantomReferenceExample() {
		Person john = new Person();

		ReferenceQueue<Person> phantomRefQueue = new ReferenceQueue<>();
		PhantomReference<Person> phantomPersonRef = new PhantomReference<>(john, phantomRefQueue);

		Person phantomPerson = phantomPersonRef.get();

		log(BORDER);
		log("john: " + john);
		log("phantomPerson: " + phantomPerson);
		log(BORDER);

		john = null;
		phantomPerson = null;
		log(REFERENCE_CLEARED);

		log(BORDER);
		log("john: " + john);
		log("phantomPerson: " + phantomPerson);
		log(BORDER);

		System.gc();
		log(GC_CALLED);

		log(REASSIGN_REFERENT);
		phantomPerson = phantomPersonRef.get();

		log(BORDER);
		log("john: " + john);
		log("phantomPerson: " + phantomPerson);
		log(BORDER);

		Reference<? extends Person> phantomPersionRefFromQueue = phantomRefQueue.poll();

		log(BORDER);
		log("phantomPersionRefFromQueue: " + phantomPersionRefFromQueue);
		log(BORDER);
	}

	private static void log(String string) {
		System.out.println(string);
	}

}