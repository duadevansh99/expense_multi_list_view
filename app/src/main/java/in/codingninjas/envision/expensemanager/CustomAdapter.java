package in.codingninjas.envision.expensemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context mContext;
    ArrayList<Item> itemsList;
    LayoutInflater layoutInflater;


    // Constructor
    public CustomAdapter(Context context, ArrayList<Item> itemsList){
        super(context,0);
        mContext = context;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemsList = itemsList;
    }

    // This function return the number of different type of views that will be there in the list view.
    @Override
    public int getViewTypeCount(){
        return 2;
    }

    // This function returns the type of item(in our case header or list item) that adapter wants to know in getView function.
    @Override
    public int getItemViewType(int position) {
        return itemsList.get(position).getItemType();
    }

    // This function gives the total count of items that will be there in the list.
    @Override
    public int getCount() {
        return itemsList.size();
    }

    // This function returns the object of the itemList that has to inflated at that position.
    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }


    // This function returns the unique id associated with every inflated layout, since it's is not useful in our case so
    // we return the position, which is also unique for every item.
    @Override
    public long getItemId(int position) {
        return position;
    }




    // This is the function in which we have to inflate the layout as per its TYPE
    // this function gets the type of each item from getItemViewType and on the basis of it we apply if else and inflate the layout.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View output;

        if(getItemViewType(position) == 0){
            output = layoutInflater.inflate(R.layout.header_item_layout,parent,false);

            HeaderItem headerItem=(HeaderItem) itemsList.get(position);

            TextView title=output.findViewById(R.id.headerTitleTextView);

            HeaderItemViewHolder holder =new HeaderItemViewHolder(title);

            holder.headerTitleTextView.setText(headerItem.getHeaderTitle());

        }
        else {
            output = layoutInflater.inflate(R.layout.expense_row_layout,parent,false);

            TextView expenseName=output.findViewById(R.id.expenseName);
            TextView expenseAmount=output.findViewById(R.id.expenseAmount);
            TextView expenseTime=output.findViewById(R.id.expenseTime);
            TextView expenseDate=output.findViewById(R.id.expenseDate);

            Expense obj=(Expense) itemsList.get(position);
            ExpenseViewHolder expenseViewHolder = new ExpenseViewHolder();

            expenseViewHolder.title = expenseName;
            expenseViewHolder.time = expenseTime;
            expenseViewHolder.date = expenseDate;
            expenseViewHolder.amount = expenseAmount;

            expenseViewHolder.title.setText(obj.getName());
            expenseViewHolder.amount.setText(obj.getAmount());
            expenseViewHolder.date.setText(obj.getDate());
            expenseViewHolder.time.setText(obj.getTime());

        }
        return output;

    }


}
