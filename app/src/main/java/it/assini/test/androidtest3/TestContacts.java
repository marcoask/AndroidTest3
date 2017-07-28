package it.assini.test.androidtest3;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

public class TestContacts extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_contacts);
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    //Query phone here.  Covered next
                }


/**
                String poBox = cur.getString(
                        cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
                String street = cur.getString(
                        cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                String city = cur.getString(
                        cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                String state = cur.getString(
                        cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                String postalCode = cur.getString(
                        cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                String country = cur.getString(
                        cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                String type = cur.getString(
                        cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));

 */
            }

            //cur.close();
        }
    }
}
