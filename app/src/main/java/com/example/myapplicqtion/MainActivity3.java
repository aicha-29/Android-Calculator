package com.example.myapplicqtion;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity3 extends AppCompatActivity {
    RelativeLayout linearLayout;
    Button btn5;
    private EditText display;
    private StringBuilder input = new StringBuilder();
    private double previousResult = 0;  // Variable pour stocker le dernier résultat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        display = findViewById(R.id.display);

        // Boutons pour les chiffres et opérations
        setButtonListener(R.id.button0);
        setButtonListener(R.id.button1);
        setButtonListener(R.id.buttonAc);
        setButtonListener(R.id.button7);
        setButtonListener(R.id.button8);
        setButtonListener(R.id.divBtn);
        setButtonListener(R.id.buttonNeuf);
        setButtonListener(R.id.buttonn);
        setButtonListener(R.id.buttonHuit);
        setButtonListener(R.id.multiBtn);
        setButtonListener(R.id.plusBtn);
        setButtonListener(R.id.button5);
        setButtonListener(R.id.buttonPen);
        setButtonListener(R.id.buttonnn);
        setButtonListener(R.id.virguleBtn);
        setButtonListener(R.id.buttonBtn);
        setButtonListener(R.id.buttonAC);
        setButtonListener(R.id.moinsBtn);
        setButtonListener(R.id.multiBtn);
        setButtonListener(R.id.divBtn);

        // Bouton de suppression
        findViewById(R.id.buttonAc).setOnClickListener(v -> {
            input.setLength(0); // Réinitialise l'entrée
            display.setText("");
        });

        // Bouton égal pour effectuer le calcul
        findViewById(R.id.equalBtn).setOnClickListener(v -> {
            try {
                double result = calculate(input.toString());
                display.setText(String.valueOf(result));
                input.setLength(0); // Réinitialise l'entrée après le calcul
                previousResult = result; // Mise à jour du dernier résultat
            } catch (Exception e) {
                Toast.makeText(MainActivity3.this, "Invalid Expression", Toast.LENGTH_SHORT).show();
            }
        });

        // Bouton btn5 qui redirige vers MainActivity2
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(v -> {
            Intent intent4 = new Intent(MainActivity3.this, MainActivity2.class);
            startActivity(intent4);
        });


    }
    // Méthode pour ajouter un écouteur aux boutons

    private void setButtonListener(int buttonId) {
        Button button = findViewById(buttonId);
        if (button != null) { // Vérifie que le bouton existe
            button.setOnClickListener(v -> {
                String value = button.getText().toString();
                if (!value.isEmpty()) {
                    input.append(value);
                    display.setText(input.toString());
                }
            });
        }
    }


    // Méthode pour effectuer le calcul simple
    private double calculate(String expression) {
        double result = 0;
        char lastOperator = '+';
        double currentNumber = 0;
        double intermediateResult = 0; // Pour gérer les multiplications et divisions en priorité

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            // Construire le nombre courant
            if (Character.isDigit(currentChar)) {
                currentNumber = currentNumber * 10 + (currentChar - '0');
            }

            // Vérifier les opérateurs ou la fin de l'expression
            if (!Character.isDigit(currentChar) && currentChar != ' ' || i == expression.length() - 1) {
                switch (lastOperator) {
                    case '+':
                        result += intermediateResult;
                        intermediateResult = currentNumber; // Préparer le nombre pour la prochaine opération
                        break;
                    case '-':
                        result += intermediateResult;
                        intermediateResult = -currentNumber; // Soustraction
                        break;
                    case '*':
                        intermediateResult *= currentNumber;
                        break;
                    case '/':
                        if (currentNumber != 0) {
                            intermediateResult /= currentNumber;
                        } else {
                            throw new ArithmeticException("Cannot divide by zero");
                        }
                        break;
                    case '%':
                        intermediateResult %= currentNumber;
                        break;
                }
                // Mettre à jour pour le prochain cycle
                lastOperator = currentChar;
                currentNumber = 0;
            }
        }

        // Ajouter le dernier résultat intermédiaire
        result += intermediateResult;

        return result;
    }





    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ConstraintLayout linearLayout = findViewById(R.id.main);

        // Si l'item est "Standard"
        if (id == R.id.calc_scientifique) {
            // Afficher un Snackbar pour l'option "Standard"
            Snackbar.make(linearLayout, "Are you sure", Snackbar.LENGTH_LONG)
                    .setAction("Undo", view -> {
                        Snackbar.make(linearLayout,"Opération effectuée", Snackbar.LENGTH_LONG)
                                .setActionTextColor(Color.RED).setTextColor(Color.YELLOW).show();
                        startActivity(new Intent(MainActivity3.this,MainActivity2.class));
                    }) .setActionTextColor(Color.RED).setTextColor(Color.GREEN).show();

            return true;
        } else if (id == R.id.calc_scientifique) {
            //operation
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}