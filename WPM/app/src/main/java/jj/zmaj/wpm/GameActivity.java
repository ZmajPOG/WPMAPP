package jj.zmaj.wpm; // update package if needed

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.content.Context;
import android.media.SoundPool;
import android.media.AudioAttributes;





public class GameActivity extends AppCompatActivity {

    private View rootLayout;
    private int lastBgColor = 0;


    private TextView tvWords, tvTimer, tvWpm, tvResult, tvAcc, tvErr;
    private EditText etInput;
    private Button btnRestart;
    private Button btnShare;
    private ImageView ivRocket, ivExhaust;
    private FrameLayout rocketArea;
    private View bgOverlay;
    private SoundPool soundPool;
    private int soundBuzz;

    private final String[] words = {
            "the", "be", "to", "of", "and", "a", "in", "that", "have", "i",
            "it", "for", "not", "on", "with", "he", "as", "you", "do", "at",
            "this", "but", "his", "by", "from", "they", "we", "say", "her", "she",
            "or", "an", "will", "my", "one", "all", "would", "there", "their",
            "what", "so", "up", "out", "if", "about", "who", "get", "which", "go", "me",
            "when", "make", "can", "like", "time", "no", "just", "him", "know", "take",
            "people", "into", "year", "your", "good", "some", "could", "them", "see", "other",
            "than", "then", "now", "look", "only", "come", "its", "over", "think", "also",
            "back", "after", "use", "two", "how", "our", "work", "first", "well", "way",
            "even", "new", "want", "because", "any", "these", "give", "day", "most", "us",
            "is", "are", "was", "were", "had", "has", "am", "did", "been", "more",
            "may", "should", "very", "must", "might", "shall", "here", "every", "find", "long",
            "little", "much", "right", "still", "too", "mean", "place", "such", "why", "help",
            "own", "same", "tell", "men", "great", "big", "high", "old", "something", "woman",
            "life", "child", "world", "school", "state", "family", "student", "group", "country", "problem",
            "hand", "part", "case", "week", "company", "system", "program", "question", "work", "number",
            "night", "point", "home", "water", "room", "mother", "area", "money", "story", "fact",
            "month", "lot", "right", "study", "book", "eye", "job", "word", "business", "issue",
            "side", "kind", "head", "house", "service", "friend", "father", "power", "hour", "game",
            "line", "end", "member", "law", "car", "city", "community", "name", "president", "team",
            "minute", "idea", "kid", "body", "information", "parent", "face", "others", "level", "office",
            "door", "health", "person", "art", "war", "history", "party", "result", "change", "morning",
            "reason", "research", "girl", "guy", "moment", "air", "teacher", "force", "education", "foot",
            "boy", "age", "policy", "process", "music", "market", "sense", "nation", "plan", "college",
            "interest", "death", "experience", "effect", "use", "class", "control", "care", "field", "development",
            "role", "effort", "rate", "heart", "drug", "show", "leader", "light", "voice", "wife",
            "police", "mind", "price", "report", "decision", "son", "view", "relationship", "town", "road",
            "arm", "difference", "value", "building", "action", "model", "season", "society", "tax", "director",
            "position", "player", "record", "paper", "space", "ground", "form", "event", "official", "matter",
            "center", "couple", "site", "project", "activity", "star", "table", "need", "court", "oil",
            "situation", "cost", "industry", "figure", "street", "image", "phone", "data", "picture", "practice",
            "piece", "land", "product", "doctor", "wall", "patient", "worker", "news", "test", "movie",
            "north", "love", "support", "technology", "step", "baby", "computer", "type", "attention", "film",
            "tree", "source", "organization", "hair", "look", "window", "culture", "chance", "brother", "energy",
            "period", "course", "summer", "plant", "opportunity", "term", "letter", "choice", "place", "rule",
            "quality", "plan", "county", "sound", "meeting", "chair", "danger", "fish", "career", "approach",
            "attack", "trip", "degree", "box", "site", "animal", "stock", "card", "wind", "exchange",
            "board", "science", "target", "structure", "purpose", "section", "risk", "region", "manager", "weight",
            "element", "charge", "operation", "budget", "transport", "style", "method", "fund", "sign", "crime",
            "benefit", "network", "station", "strategy", "success", "half", "degree", "contract", "player", "trend",
            "technology", "army", "instance", "item", "direction", "agreement", "reality", "teacher", "capital", "yard",
            "task", "medium", "income", "truth", "solution", "chance", "career", "impact", "source", "advice",
            "seat", "version", "population", "sample", "signal", "student", "effort", "category", "context", "partner",
            "reference", "king", "community", "object", "shot", "function", "structure", "aspect", "author", "background",
            "campaign", "contract", "document", "education", "environment", "factor", "feature", "goal", "growth", "knowledge",
            "limit", "location", "machine", "maintenance", "manufacturer", "nature", "operation", "option", "organization", "package",
            "policy", "practice", "procedure", "process", "product", "project", "property", "proposal", "purpose", "quality",
            "region", "requirement", "resource", "response", "result", "role", "schedule", "section", "service", "skill",
            "solution", "source", "strategy", "structure", "system", "task", "technology", "theory", "tool", "topic",
            "user", "value", "variation", "version", "vision", "volume", "week", "year", "ability", "access",
            "account", "activity", "addition", "address", "administration", "adult", "advantage", "advertising", "affair", "agency",
            "agent", "agreement", "airline", "airport", "alarm", "album", "alcohol", "alternative", "ambition", "analysis",
            "analyst", "animal", "answer", "apartment", "appeal", "appearance", "application", "appointment", "area", "argument",
            "arrival", "artist", "aspect", "assignment", "assistant", "association", "assumption", "atmosphere", "attempt", "attention",
            "attitude", "audience", "author", "average", "award", "background", "balance", "bank", "bar", "base",
            "basis", "basket", "battle", "beach", "beauty", "beginning", "benefit", "bet", "bill", "bird",
            "birth", "bit", "block", "blood", "board", "boat", "body", "bonus", "border", "boss",
            "bottle", "bottom", "bowl", "boyfriend", "branch", "brand", "bread", "break", "breath", "brick",
            "bridge", "brief", "brother", "budget", "building", "bunch", "burn", "bus", "business", "buyer",
            "cabinet", "camera", "campaign", "candidate", "capacity", "capital", "capture", "carbon", "career", "category",
            "celebration", "cell", "center", "ceremony", "chain", "challenge", "champion", "channel", "chapter", "character",
            "charge", "charity", "chart", "check", "chef", "chemical", "childhood", "chip", "choice", "church",
            "circle", "circumstance", "citizen", "city", "claim", "class", "classic", "climate", "clinic", "clock",
            "clothes", "cloud", "club", "coach", "coal", "coast", "code", "coffee", "collection", "combination",
            "comfort", "command", "comment", "commission", "committee", "communication", "community", "company", "comparison", "competition",
            "complaint", "complex", "component", "concept", "concern", "concert", "conclusion", "condition", "conference", "confidence",
            "conflict", "confusion", "connection", "consequence", "consideration", "construction", "contact", "context", "contract", "contribution",
            "control", "conversation", "conversion", "cookie", "cooperation", "copy", "corner", "corporation", "correct", "cost",
            "council", "counter", "country", "county", "couple", "courage", "course", "court", "cousin", "cover",
            "crack", "craft", "crash", "creation", "creature", "credit", "crew", "crime", "crisis", "criteria",
            "critic", "criticism", "crowd", "culture", "cup", "currency", "current", "customer", "cycle", "dad",
            "damage", "dance", "danger", "data", "database", "date", "daughter", "day", "deadline", "deal",
            "dealer", "debate", "debt", "decision", "definition", "degree", "delivery", "demand", "department", "departure",
            "deposit", "depression", "depth", "description", "design", "designer", "desire", "desk", "detail", "development",
            "device", "devil", "diagram", "dialogue", "diamond", "diary", "diet", "difference", "difficulty", "direction",
            "director", "dirt", "disaster", "discipline", "discount", "discussion", "disease", "dish", "disk", "display",
            "distance", "district", "doctor", "document", "dog", "door", "double", "doubt", "draft", "drama",
            "drawer", "drawing", "dream", "dress", "drink", "drive", "driver", "drop", "drug", "drum",
            "duck", "dust", "duty", "earth", "economics", "economist", "economy", "edition", "editor", "education",
            "effect", "efficiency", "effort", "egg", "election", "electricity", "element", "emergency", "emotion", "emphasis",
            "employee", "employer", "employment", "encounter", "energy", "engine", "engineer", "engineering", "entertainment", "enthusiasm",
            "entrance", "entry", "environment", "episode", "equation", "equipment", "equivalent", "error", "escape", "essay",
            "essence", "estate", "estimate", "ethics", "event", "evidence", "exam", "examination", "example", "excitement",
            "exercise", "exhibition", "existence", "expansion", "expectation", "expense", "experience", "experiment", "expert", "explanation",
            "expression", "extent", "external", "extent", "face", "facility", "fact", "factor", "failure", "faith",
            "fall", "fame", "family", "fan", "farm", "farmer", "fashion", "fat", "fate", "fault",
            "favor", "fear", "feature", "feedback", "fee", "feeling", "female", "fence", "festival", "fiction",
            "field", "fight", "figure", "file", "film", "finance", "finding", "finger", "finish", "fire",
            "fish", "flight", "focus", "fold", "following", "food", "foot", "force", "forehead", "forest",
            "fortune", "foundation", "frame", "freedom", "friendship", "front", "fruit", "fuel", "function", "fund",
            "funeral", "future", "gain", "game", "gap", "garage", "garden", "garlic", "gas", "gate",
            "gathering", "gear", "gene", "general", "generation", "gift", "girl", "glance", "glass", "glove",
            "goal", "gold", "government", "grade", "grain", "grandfather", "grandmother", "grass", "growth", "guest",
            "guidance", "guide", "guilt", "habit", "hair", "half", "hall", "hand", "handle", "happiness",
            "harbor", "harm", "hat", "head", "health", "hearing", "heart", "heat", "height", "hell",
            "help", "hero", "hide", "highlight", "hill", "hint", "historian", "history", "holiday", "home",
            "homework", "honor", "hook", "hope", "horror", "horse", "hospital", "host", "hotel", "hour",
            "house", "housing", "human", "humor", "hunting", "hurry", "hurt", "ice", "idea", "identity",
            "impact", "importance", "impression", "improvement", "incident", "income", "increase", "independence", "indication", "individual",
            "industry", "infection", "inflation", "influence", "information", "initiative", "injury", "insect", "inside", "insight",
            "inspection", "instance", "instruction", "insurance", "intention", "interaction", "interest", "interior", "international", "internet",
            "interview", "introduction", "invasion", "investment", "invitation", "iron", "island", "issue", "item", "jacket",
            "jail", "jam", "job", "joint", "joke", "journal", "journey", "judge", "judgment", "juice",
            "jump", "junior", "jury", "keep", "key", "kick", "kid", "kill", "kind", "king",
            "kitchen", "knee", "knife", "knowledge", "labor", "lack", "lady", "lake", "land", "landscape",
            "language", "laughter", "law", "layer", "leader", "leadership", "league", "leather", "lecture", "length",
            "lesson", "letter", "level", "library", "license", "life", "lift", "light", "limit", "line",
            "link", "list", "literature", "location", "lock", "log", "logic", "look", "loss", "love",
            "luck", "lunch", "machine", "magazine", "magic", "mail", "main", "maintenance", "major", "make",
            "male", "mall", "man", "management", "manager", "manner", "manufacturer", "map", "mark", "market",
            "marriage", "material", "math", "matter", "maximum", "meal", "meaning", "measurement", "meat", "media",
            "medicine", "medium", "meeting", "member", "membership", "memory", "mention", "menu", "message", "metal",
            "method", "middle", "might", "milk", "mind", "mine", "minimum", "minute", "mirror", "mission",
            "mistake", "mixture", "mode", "model", "moment", "money", "monitor", "month", "mood", "morning",
            "mortgage", "mother", "motion", "motor", "mountain", "mouse", "mouth", "move", "movement", "movie",
            "mud", "muscle", "music", "nail", "name", "narrative", "nation", "nature", "neighborhood", "nerve",
            "net", "network", "news", "newspaper", "night", "noise", "normal", "note", "nothing", "notice",
            "novel", "number", "object", "objective", "obligation", "observation", "occasion", "offer", "office", "officer",
            "oil", "opening", "operation", "opinion", "opportunity", "opposite", "option", "orange", "order", "organization",
            "origin", "outcome", "outlet", "output", "outside", "owner", "pace", "package", "page", "pain",
            "painting", "pair", "panel", "paper", "parent", "park", "part", "participant", "partnership", "party",
            "passage", "passenger", "passion", "past", "path", "patience", "patient", "pattern", "pause", "payment",
            "peace", "peak", "pen", "penalty", "people", "percentage", "perception", "performance", "period", "permission",
            "person", "personality", "perspective", "phase", "phenomenon", "philosophy", "phone", "photo", "physical", "physics",
            "piano", "picture", "piece", "pile", "pilot", "pipe", "pitch", "place", "plan", "plane",
            "plant", "plastic", "plate", "platform", "player", "pleasure", "plenty", "poem", "poet", "poetry",
            "point", "police", "policy", "politics", "pollution", "pool", "population", "position", "positive", "possession",
            "possibility", "post", "pot", "potential", "pound", "power", "practice", "preference", "preparation", "presence",
            "presentation", "president", "press", "pressure", "price", "pride", "primary", "principle", "print", "priority",
            "prison", "privacy", "privilege", "prize", "problem", "procedure", "process", "produce", "product", "profession",
            "professional", "professor", "profile", "profit", "program", "progress", "project", "promise", "promotion", "proof",
            "property", "proposal", "prospect", "protection", "protein", "protest", "psychology", "public", "publication", "purpose",
            "pursuit", "quantity", "quarter", "queen", "question", "quiet", "quote", "race", "radio", "rain",
            "range", "rate", "ratio", "reaction", "reader", "reading", "reality", "reason", "reception", "recognition"

    };
    private String[] currentWords = new String[3];
    private int wordsTyped = 0, charsTyped = 0, errors = 0, totalTyped = 0;
    private boolean testStarted = false;
    private CountDownTimer timer;
    private long timeLeft;
    //private Handler exhaustHandler = new Handler();
    private final Handler exhaustHandler = new Handler();
    private int rocketOffset = 0; // px to move rocket up
    private int rocketMaxOffset = 2700; // how far up can rocket go

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Sound setup
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(1)
                .build();

        soundBuzz = soundPool.load(this, R.raw.buzz, 1);


        rootLayout = findViewById(R.id.rootLayout);

        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(v -> shareResults());

        lastBgColor = Color.parseColor("#87ceeb"); // starting color


        tvWords = findViewById(R.id.tvWords);
        tvTimer = findViewById(R.id.tvTimer);
        tvWpm = findViewById(R.id.tvWpm);
        tvResult = findViewById(R.id.tvResult);
        tvAcc = findViewById(R.id.tvAcc);
        tvErr = findViewById(R.id.tvErr);
        etInput = findViewById(R.id.etInput);
        btnRestart = findViewById(R.id.btnRestart);
        ivRocket = findViewById(R.id.ivRocket);
        ivExhaust = findViewById(R.id.ivExhaust);
        rocketArea = findViewById(R.id.rocketArea);
        bgOverlay = findViewById(R.id.bgOverlay);

        btnRestart.setOnClickListener(v -> restartTest());

        etInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!testStarted && s.length() > 0) startTest();
                updateWordDisplay(s.toString());
                if (testStarted && s.toString().endsWith(" ")) {
                    String typed = s.toString().trim();
                    if (typed.equals(currentWords[0])) {
                        wordsTyped++;
                        charsTyped += currentWords[0].length();
                        rocketBoost();
                        showExhaust();
                        animateSkyToSpace();
                    } else {
                        errors++;
                        vibrateOnError();
                        soundPool.play(soundBuzz, 1, 1, 0, 0, 1);
                    }
                    totalTyped++;
                    shiftWords();
                    etInput.setText("");
                    updateStats();
                }
            }
        });

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        int savedTime = prefs.getInt("timeLimit", 60); // default 60
        timeLeft = savedTime * 1000L;


        resetFields();
        loadWords();
        updateWordDisplay("");
    }


    private void vibrateOnError() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(80, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(80);
            }
        }
    }


    private void rocketBoost() {
        int nextOffset = rocketOffset + 45; // how much up per correct word
        if (nextOffset > rocketMaxOffset) nextOffset = rocketMaxOffset;

        // Animate the rocket upward
        TranslateAnimation anim = new TranslateAnimation(0, 0, rocketOffset, nextOffset);
        anim.setDuration(200);
        anim.setFillAfter(true);
        ivRocket.startAnimation(anim);

        // Only move the rocket's translation, NOT the exhaust
        rocketOffset = nextOffset;
        ivRocket.setTranslationY(-rocketOffset);
        // Do NOT move ivExhaust! Keep it at its fixed position.
    }


    /*
    private void rocketBoost() {
        int nextOffset = rocketOffset + 45; // how much up per correct word
        if (nextOffset > rocketMaxOffset) nextOffset = rocketMaxOffset;
        TranslateAnimation anim = new TranslateAnimation(0,0,rocketOffset, nextOffset);
        anim.setDuration(200);
        anim.setFillAfter(true);
        ivRocket.startAnimation(anim);
        // Remove exhaust animation! Only move with translation
        ivExhaust.setTranslationY(-rocketOffset);
        rocketOffset = nextOffset;
        ivRocket.setTranslationY(-rocketOffset);
    }*/


    /*
    private void rocketBoost() {
        int nextOffset = rocketOffset + 45; // how much up per correct word
        if (nextOffset > rocketMaxOffset) nextOffset = rocketMaxOffset;
        TranslateAnimation anim = new TranslateAnimation(0,0,rocketOffset, nextOffset);
        anim.setDuration(200);
        anim.setFillAfter(true);
        ivRocket.startAnimation(anim);
        //ivExhaust.startAnimation(anim);
        ivExhaust.setTranslationY(-rocketOffset);
        rocketOffset = nextOffset;
        // Move rocketArea up to simulate background moving down
        ivRocket.setTranslationY(-rocketOffset);
        ivExhaust.setTranslationY(-rocketOffset);
    }*/




    private void animateSkyToSpace() {
        float percent = Math.min(1f, rocketOffset / (float) rocketMaxOffset);

        // Use easing if desired, or just linear:
        // float slowPercent = percent * percent; // "ease in"
        float slowPercent = percent; // LINEAR: goes to full black at end!

        int startColor = Color.parseColor("#87ceeb");
        int endColor = Color.parseColor("#14182b");
        int targetColor = (int) new ArgbEvaluator().evaluate(slowPercent, startColor, endColor);

        ValueAnimator colorAnim = ValueAnimator.ofObject(new ArgbEvaluator(), lastBgColor, targetColor);
        colorAnim.setDuration(400);
        colorAnim.addUpdateListener(animator -> {
            int color = (int) animator.getAnimatedValue();
            rootLayout.setBackgroundColor(color);
            lastBgColor = color;
        });
        colorAnim.start();
    }

    /*
    private void animateSkyToSpace() {
        float percent = Math.min(1f, rocketOffset / (float) rocketMaxOffset);

        // Make darkening more subtle: reduce percent for background color
        float slowPercent = percent * 0.4f; // 0.4 makes it only 40% as dark even at max

        int startColor = Color.parseColor("#87ceeb");
        int endColor = Color.parseColor("#14182b");
        int targetColor = (int) new ArgbEvaluator().evaluate(slowPercent, startColor, endColor);

        // Animate from lastBgColor to targetColor for a smooth transition
        ValueAnimator colorAnim = ValueAnimator.ofObject(new ArgbEvaluator(), lastBgColor, targetColor);
        colorAnim.setDuration(400);
        colorAnim.addUpdateListener(animator -> {
            int color = (int) animator.getAnimatedValue();
            rootLayout.setBackgroundColor(color);
            lastBgColor = color;
        });
        colorAnim.start();
    }*/







/*
    private void animateSkyToSpace() {
        float percent = Math.min(1f, rocketOffset / (float) rocketMaxOffset);
        int startColor = Color.parseColor("#87ceeb");   // sky blue
        int endColor = Color.parseColor("#14182b");     // deep space blue
        int color = (int) new ArgbEvaluator().evaluate(percent, startColor, endColor);
        rootLayout.setBackgroundColor(color);
    }*/


    /*
    private void animateSkyToSpace() {
        // Animate background to get darker as rocket rises
        float percent = Math.min(1f, rocketOffset / (float) rocketMaxOffset);
        int startColor = Color.parseColor("#87ceeb");
        int endColor = Color.parseColor("#14182b");
        ValueAnimator colorAnim = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, endColor);
        colorAnim.setDuration(400);
        colorAnim.addUpdateListener(animator -> bgOverlay.setBackgroundColor((int) animator.getAnimatedValue()));
        colorAnim.start();
    }*/


    //private final Handler exhaustHandler = new Handler();

    private void showExhaust() {
        ivExhaust.setVisibility(View.VISIBLE);
        exhaustHandler.removeCallbacksAndMessages(null);
        exhaustHandler.postDelayed(() -> ivExhaust.setVisibility(View.GONE), 300);
    }

    /*
    private void showExhaust() {
        ivExhaust.setVisibility(View.VISIBLE);
        exhaustHandler.removeCallbacksAndMessages(null);
        exhaustHandler.postDelayed(() -> ivExhaust.setVisibility(View.GONE), 300);
    }*/

    private void startTest() {
        testStarted = true;
        timer = new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                tvTimer.setText("Time: " + (millisUntilFinished / 1000) + "s");
                updateStats();
            }
            public void onFinish() {
                tvTimer.setText("Time: 0s");
                endTest();
            }
        }.start();
        tvResult.setText("");
        btnRestart.setVisibility(View.GONE);
    }

    private void shiftWords() {
        // Move all words left, load a new one at the end
        currentWords[0] = currentWords[1];
        currentWords[1] = currentWords[2];
        currentWords[2] = randomWord();
    }

    private void loadWords() {
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            currentWords[i] = words[r.nextInt(words.length)];
        }
    }

    private String randomWord() {
        Random r = new Random();
        return words[r.nextInt(words.length)];
    }

    private void updateWordDisplay(String typedSoFar) {
        // Show four words, highlight correct/incorrect letters of current word, caret at end
        SpannableStringBuilder sb = new SpannableStringBuilder();

        // --- Highlight first word ---
        String word = currentWords[0];
        int min = Math.min(typedSoFar.length(), word.length());
        boolean mistakeFound = false;
        for (int i = 0; i < word.length(); i++) {
            int start = sb.length();
            char c = word.charAt(i);
            if (i < typedSoFar.length()) {
                char t = typedSoFar.charAt(i);
                if (t == c && !mistakeFound) {
                    // Vibrant green for correct, with shadow
                    sb.append(String.valueOf(c), new ForegroundColorSpan(Color.parseColor("#15ff45")), 0);
                } else {
                    // Vibrant red for first and all next errors
                    sb.append(String.valueOf(c), new ForegroundColorSpan(Color.parseColor("#ff365a")), 0);
                    mistakeFound = true;
                }
            } else {
                // Remaining: white
                sb.append(String.valueOf(c), new ForegroundColorSpan(Color.parseColor("#ffffff")), 0);
            }
        }

        // Append blinking caret at the end
        sb.append(" |", new ForegroundColorSpan(Color.parseColor("#64b5ff")), 0);

        // Add rest of the words (plain white, spaced)
        sb.append("   ");
        for (int i = 1; i < 3; i++) {
            sb.append(currentWords[i]).append("   ");
        }
        tvWords.setText(sb);
    }

    private void updateStats() {
        int secondsPassed = (int)((60000 - timeLeft) / 1000);
        double minutes = secondsPassed / 60.0;
        int wpm = minutes > 0 ? (int)(wordsTyped / minutes) : 0;
        int accuracy = totalTyped > 0 ? (int)(100.0 * wordsTyped / totalTyped) : 100;
        tvWpm.setText("WPM: " + wpm);
        tvAcc.setText("Acc: " + accuracy + "%");
        tvErr.setText("Err: " + errors);
    }

    private void endTest() {
        testStarted = false;
        etInput.setEnabled(false);

        int finalWpm = wordsTyped;
        int accuracy = totalTyped > 0 ? (int)(100.0 * wordsTyped / totalTyped) : 100;
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        int savedTime = prefs.getInt("timeLimit", 60);
        String duration = savedTime + " sec (" + ((savedTime * 1000 - timeLeft) / 1000) + ")";


        tvResult.setText("Time's up!\nWPM: " + finalWpm + "\nErrors: " + errors + "\nAccuracy: " + accuracy + "%");
        btnRestart.setVisibility(View.VISIBLE);

        btnShare.setVisibility(View.VISIBLE);


        // ---- Save Attempt to SharedPreferences ----
        saveAttempt(finalWpm, accuracy, errors, duration);
    }

    private void saveAttempt(int wpm, int accuracy, int errors, String duration) {
        try {
            SharedPreferences prefs = getSharedPreferences("history", MODE_PRIVATE);
            String raw = prefs.getString("attempts", "[]");
            JSONArray arr = new JSONArray(raw);

            // Build new array with new attempt at front
            JSONArray newArr = new JSONArray();
            JSONObject obj = new JSONObject();
            obj.put("wpm", wpm);
            obj.put("accuracy", accuracy);
            obj.put("errors", errors);
            obj.put("duration", duration);

            newArr.put(obj); // first: new attempt
            for (int i = 0; i < arr.length() && i < 29; i++) {
                newArr.put(arr.getJSONObject(i)); // copy up to 29
            }

            prefs.edit().putString("attempts", newArr.toString()).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


/*
    private void saveAttempt(int wpm, int accuracy, int errors, String duration) {
        try {
            SharedPreferences prefs = getSharedPreferences("history", MODE_PRIVATE);
            String raw = prefs.getString("attempts", "[]");
            JSONArray arr = new JSONArray(raw);

            // New attempt at front
            JSONObject obj = new JSONObject();
            obj.put("wpm", wpm);
            obj.put("accuracy", accuracy);
            obj.put("errors", errors);
            obj.put("duration", duration);
            arr.put(0, obj);

            // Keep only last 10
            while (arr.length() > 10) arr.remove(arr.length() - 1);

            prefs.edit().putString("attempts", arr.toString()).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/



    private void restartTest() {
        if (timer != null) timer.cancel();
        resetFields();
        loadWords();
        updateWordDisplay("");
        etInput.setEnabled(true);
        etInput.setText("");
        etInput.requestFocus();
        btnRestart.setVisibility(View.GONE);
        tvResult.setText("");
        // Reset rocket and background
        rocketOffset = 0;
        rocketArea.setTranslationY(0);
        bgOverlay.setBackgroundResource(R.drawable.gradient_sky_space);
        lastBgColor = Color.parseColor("#87ceeb");
        rootLayout.setBackgroundColor(lastBgColor);
        btnShare.setVisibility(View.GONE);

    }

    private void resetFields() {
        wordsTyped = 0;
        charsTyped = 0;
        errors = 0;
        totalTyped = 0;

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        int savedTime = prefs.getInt("timeLimit", 60); // default 60
        timeLeft = savedTime * 1000;
        tvTimer.setText("Time: " + savedTime + "s");


        tvWpm.setText("WPM: 0");
        tvAcc.setText("Acc: 100%");
        tvErr.setText("Err: 0");
        testStarted = false;
    }

    private void shareResults() {
        String shareText = "My TypeRush score:\n"
                + tvWpm.getText().toString() + "\n"
                + tvAcc.getText().toString() + "\n"
                + tvErr.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(intent, "Share your score"));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }


}
