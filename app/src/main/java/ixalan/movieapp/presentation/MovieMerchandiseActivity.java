package ixalan.movieapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ixalan.movieapp.R;
import ixalan.movieapp.business.AccessCart;
import ixalan.movieapp.business.AccessMerchandise;
import ixalan.movieapp.business.AccessMovies;
import ixalan.movieapp.objects.CartItem;
import ixalan.movieapp.objects.Merchandise;
import ixalan.movieapp.objects.Movie;

public class MovieMerchandiseActivity extends AppCompatActivity
{
    private AccessMerchandise accessMerchandise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_merchandise);

        Intent intent = getIntent();

        if (intent != null)
        {
            Movie movie = (Movie) getIntent().getSerializableExtra("MOVIE");
            accessMerchandise = new AccessMerchandise(movie);

            showMerchandiseItem(accessMerchandise.getNextItem());

            adjustQuantity();

            Button addQtyBtn = (Button)findViewById(R.id.addQty);
            addQtyBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    changeQuantity(true);
                }
            });

            Button rmvQtyBtn = (Button)findViewById(R.id.rmvQty);
            rmvQtyBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    changeQuantity(false);
                }
            });

            Button showNextBtn = (Button)findViewById(R.id.showNextButton);
            //if clicked, display movie details
            showNextBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    showMerchandiseItem(accessMerchandise.getNextItem());
                }
            });

            Button addToCart = (Button)findViewById(R.id.addToCartButton);
            addToCart.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    addToCart();
                }
            });

            Button viewCart = (Button)findViewById(R.id.view_cart_button);
            viewCart.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    viewCart();
                }
            });
        }
    }

    public void showMerchandiseItem(Merchandise merchandise)
    {
        TextView merchandiseTitle = (TextView)findViewById(R.id.merchandise_item_title);
    }

    public void viewCart()
    {
        Intent myIntent = new Intent(MovieMerchandiseActivity.this, ViewCartActivity.class);
        startActivity(myIntent);
    }

    public void changeQuantity(boolean increaseQty)
    {
        //update quantity in AccessMerchandise
        if (increaseQty)
        {
            accessMerchandise.setQuantity(accessMerchandise.getQuantity()+1);
        }
        else
        {
            accessMerchandise.setQuantity(accessMerchandise.getQuantity()-1);
        }

        adjustQuantity();
    }

    public void adjustQuantity()
    {
        TextView merchandiseTitle = (TextView)findViewById(R.id.displayQty);
        merchandiseTitle.setText(""+accessMerchandise.getQuantity());
    }

    public void addToCart()
    {
        CartItem cartItem = accessMerchandise.getCurrentItem();
        int quantity = accessMerchandise.getQuantity();
        if (cartItem != null && quantity > 0)
        {
            cartItem.setQuantity(quantity);

            AccessCart.addCartItem(cartItem);
        }

    }
}
