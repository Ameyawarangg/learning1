package com.example.learning1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learning1.ui.theme.Learning1Theme
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize


class  MainActivity : ComponentActivity(){      // create a class called main activity containing the all the basic features which are contained in the component activity.
    override fun onCreate (savedInstanceState:Bundle?){  // we override the basic inatialization of the screens and provide manual inatialization instructions, bundle signifies that it can restore the last working state of the activity.
        super.onCreate(savedInstanceState)  // super refers to the extension of the component activity.
            setContent{
                Learning1Theme{ Personality(Traits(1,"Ameya", "IS confident")) }
                MainScreen()
        }
    }
}

data class Traits(val id: Int , val name: String, val trait : String )

@Composable
fun Personality(msg: Traits){ // msg is the name of the parameter and the traits is supposed to be a data class in which the code should work in.
   Row(modifier = Modifier.padding(all = 9.dp))
   {
       Image(painter = painterResource(id = R.drawable.user1),
           contentDescription ="IMAGE OF USER1",
           modifier = Modifier
               .size(35.dp)
               .clip(CircleShape)
               .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
       )

    Spacer(modifier = Modifier.width(9.dp))

        var isExpanded by remember {
         mutableStateOf(false)
        }
        val surfaceColor by animateColorAsState(if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,

        )

    Column(modifier=Modifier.clickable { isExpanded =! isExpanded })
       {

        Text(text = msg.name,
             color = MaterialTheme.colorScheme.secondary,
             style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(4.dp))
        Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp, 
            color = surfaceColor,
            modifier = Modifier
                .animateContentSize()
                .padding(4.dp))    // provides the black shape by creating its surface and that is defined in the material theme
        {


        Text(
            text = msg.trait,
                    modifier= Modifier.padding(3.dp),
                    maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
     }
    }
   }
    

    
}
@Preview(name ="Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewPersonality(){
    Learning1Theme{ Personality(msg = Traits(1,"AMEYA", "IS CONFIDENT")) }
}

@Composable
fun Multiplex(personalities: List<Traits>){
    LazyColumn{
        items(personalities){
            msg -> Personality(msg = msg)
        }
    }
}

@Composable
fun MainScreen(){
    val personalitiyList : List<Traits> = listOf(
       Traits(1,"Ameya","Is Confident"),
        Traits(2,"Tanya","IS Gone"),
        Traits(3, "Tanishk","IS Fat")
    )
    Multiplex(personalities = personalitiyList)

}

