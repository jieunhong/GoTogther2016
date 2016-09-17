package emirim.hs.kr.sugarexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.SugarRecord;

public class MainActivity extends AppCompatActivity {

    EditText bookTitle;
    EditText bookEdition;

    Button save;
    Button load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookEdition = (EditText)findViewById(R.id.Text2);
        bookTitle = (EditText)findViewById(R.id.Text1);
        save = (Button)findViewById(R.id.Button1);
        load = (Button)findViewById(R.id.button2);

        Book.findById(Book.class, (long) 1);
        Book book = new Book("테흐트", "ㅁㄹㄴㅇㅁㄹ");
        book.save();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = Book.findById(Book.class, 1);
                Toast.makeText(getApplicationContext(),"이름 : "+book.title+"  판 : "+book.edition,Toast.LENGTH_LONG).show();
            }
        });
    }





}
