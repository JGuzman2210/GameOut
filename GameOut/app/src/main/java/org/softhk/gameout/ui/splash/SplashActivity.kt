package org.softhk.gameout.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import org.softhk.gameout.ui.container.GameActivity
import org.softhk.gameout.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val animation = AnimationUtils.loadAnimation(
            this,
            R.anim.customs_animate
        )

        val logoImageView:ImageView = findViewById(R.id.logo_image_view)
        val textView:TextView = findViewById(R.id.logo_text_view)

        logoImageView.animation = animation
        textView.animation = animation

        Handler().postDelayed(({
            startActivity(Intent(this, GameActivity::class.java))
            this.finish()
        }),3000)
    }
}

