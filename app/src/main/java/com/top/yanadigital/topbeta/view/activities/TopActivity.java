package com.top.yanadigital.topbeta.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.top.yanadigital.topbeta.R;
import com.top.yanadigital.topbeta.model.vo.Artista;
import com.top.yanadigital.topbeta.view.MainActivity;
import com.top.yanadigital.topbeta.view.adapters.ArtistaAdapter;
import com.top.yanadigital.topbeta.view.adapters.OnItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopActivity extends AppCompatActivity implements OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.containerMain)
    CoordinatorLayout containerMain;
    private ArtistaAdapter artistaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_top);
            ButterKnife.bind(this);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            configToolbar();

            configAdapter();
            configRecyclerView();
            generateArtist();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void configToolbar() {

        try {
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configAdapter() {
        //Instanciar

        try {
            artistaAdapter = new ArtistaAdapter(new ArrayList<Artista>(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configRecyclerView() {

        try {
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
            recyclerview.setAdapter(artistaAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void generateArtist() {


        try {
            String[] nombre = {"Rachel", "Kate", "Tom", "Will"};
            String[] apellido = {"McAdams", "Beckinsale", "Hanks", "Smith"};
            long[] nacimiento = {280108800000L, 112492800000L, 425433600000L, -40003200000L};
            String[] lugares = {"Canada", "England", "USA", "USA"};
            short[] estaturas = {163, 170, 183, 188};
            String[] notas = {
                    "Rachel Anne McAdams was born on November 17, 1978 in London, Ontario, Canada, to Sandra Kay (Gale), a nurse, and Lance Frederick McAdams, a truck driver and furniture mover. She is of English, Welsh, Irish, and Scottish descent. Rachel became involved with acting as a teenager and by the age of 13 was performing in Shakespearean productions in summer theater camp; she went on to graduate with honors with a BFA degree in Theater from York University. After her debut in an episode of Disney's The Famous Jett Jackson (1998), she co-starred in the Canadian TV series Slings and Arrows (2003), a comedy-drama about the trials and travails of a Shakespearean theater group, and won a Gemini award for her performance in 2003.\n" +
                            "\n" +
                            "Her breakout role as Regina George in the hit comedy Chicas pesadas (2004) instantly catapulted her onto the short list of Hollywood's hottest young actresses. She followed that film with a star turn opposite Ryan Gosling in the adaptation of the Nicholas Sparks bestseller Diario de una pasión (2004), which was a surprise box office success and became the predominant romantic drama for a new, young generation of moviegoers. After filming, McAdams and Gosling became romantically involved and dated through mid-2007. McAdams next showcased her versatility onscreen with the manic comedy Los cazanovias (2005), the thriller Vuelo nocturno (2005), and the holiday drama The Family Stone (2005).\n" +
                            "\n" +
                            "McAdams then explored the independent film world with Infieles (2007), which premiered at the Toronto Film Festival and also starred Pierce Brosnan, Chris Cooper and Patricia Clarkson. Starring roles in the military drama The Lucky Ones (2008), the newspaper thriller Los secretos del poder (2009), and the romance Te amaré por siempre (2009) followed before she starred opposite Robert Downey Jr. and Jude Law in Guy Ritchie's international blockbuster Sherlock Holmes (2009). McAdams played the plucky producer of a failing morning TV show in Morning Glory (2010), the materialistic fiancée of Owen Wilson in Woody Allen's Medianoche en París (2011), and returned to romantic drama territory with the hit film Votos de amor (2012) opposite Channing Tatum. The actress also stars with Ben Affleck in Terrence Malick's Deberás amar (2012) and alongside Noomi Rapace in Brian De Palma's thriller Pasión, un asesinato perfecto (2012).\n" +
                            "\n" +
                            "In 2005, McAdams received ShoWest's \"Supporting Actress of the Year\" Award as well as the \"Breakthrough Actress of the Year\" at the Hollywood Film Awards. In 2009, she was awarded with ShoWest's \"Female Star of the Year.\" As of 2011, she has been romantically linked with her Medianoche en París (2011) co-star Michael Sheen.",

                    "Kate Beckinsale was born on 26 July 1973 in England, and has resided in London for most of her life. Her mother is Judy Loe, who has appeared in a number of British dramas and sitcoms and continues to work as an actress, predominantly in British television productions. Her father was Richard Beckinsale, born in Nottingham, England. He starred in a number of popular British television comedies during the 1970s, most notably the series Rising Damp (1974), Porridge (1973) and The Lovers (1970). He passed away tragically early in 1979 at the age of 31.\n" +
                            "\n" +
                            "Kate attended the private school Godolphin and Latymer School in London for her grade and primary school education. In her teens she twice won the British bookseller W.H. Smith Young Writers' competition - once for three short stories and once for three poems. After a tumultuous adolescence (a bout of anorexia - cured - and a smoking habit which continues to this day), she gradually took up the profession of acting.\n" +
                            "\n" +
                            "Her major acting debut came in a TV film about World War II called One Against the Wind (1991), filmed in Luxembourg during the summer of 1991. It first aired on American television that December. Kate began attending Oxford University's New College in the fall of 1991, majoring in French and Russian literature. She had already decided that she wanted to act, but to broaden her horizons she chose university over drama school. While in her first year at Oxford, Kate received her big break in Kenneth Branagh's film adaptation of William Shakespeare's Mucho ruido y pocas nueces (1993). Kate worked in three other films while attending Oxford, beginning with a part in the medieval historical drama Prince of Jutland (1994), cast as Ethel. The film was shot during the spring of 1993 on location in Denmark, and she filmed her supporting part during New College's Easter break. Later in the summer of that year she played the lead in the contemporary mystery drama Uncovered (1994). Before she went back to school, her third year at university was spent at Oxford's study-abroad program in Paris, France, immersing herself in the French language, Parisian culture and French cigarettes.\n" +
                            "\n" +
                            "A year away from the academic community and living on her own in the French capital caused her to re-evaluate the direction of her life. She faced a choice: continue with school or concentrate on her flourishing acting career. After much thought, she chose the acting career. In the spring of 1994 Kate left Oxford, after finishing three years of study. Kate appeared in the BBC/Thames Television satire Cold Comfort Farm (1995), filmed in London and East Sussex during late summer 1994 and which opened to spectacular reviews in the United States, grossing over $5 million during its American run. It was re-released to U.K. theaters in the spring of 1997.\n" +
                            "\n" +
                            "Acting on the stage consumed the first part of 1995; she toured in England with the Thelma Holts Theatre Company production of Anton Chekhov's \"The Seagull\". After turning down several mediocre scripts \"and going nearly berserk with boredom\", she waited seven months before another interesting role was offered to her. Her big movie of 1995 was the romance/horror movie Haunted (1995), starring opposite Aidan Quinn and John Gielgud, and filmed in West Sussex. In this film she wanted to play \"an object of desire\", unlike her past performances where her characters were much less the siren and more the worldly innocent. Kate's first film project of 1996 was the British ITV production of Jane Austen's novel Emma (1996). Her last film of 1996 was the comedy Shooting Fish (1997), filmed at Shepperton Studios in London during early fall. She played the part of Georgie, an altruistic con artist. She had a daughter, Lily, in 1999 with actor Michael Sheen.",


                    "Thomas Jeffrey Hanks was born in Concord, California, to Janet Marylyn (Frager), a hospital worker, and Amos Mefford Hanks, an itinerant cook. His mother's family, originally surnamed \"Fraga\", was entirely Portuguese, while his father was of mostly English ancestry. Tom grew up in what he has called a \"fractured\" family. He moved around a great deal after his parents' divorce, living with a succession of step-families. No problems, no alcoholism - just a confused childhood. He has no acting experience in college and credits the fact that he could not get cast in a college play with actually starting his career. He went downtown, and auditioned for a community theater play, was invited by the director of that play to go to Cleveland, and there his acting career started.\n" +
                            "\n" +
                            "Ron Howard was working on Splash (1984), a fantasy-comedy about a mermaid who falls in love with a business executive. Howard considered Hanks for the role of the main character's wisecracking brother, which eventually went to John Candy. Instead, Hanks landed the lead role and the film went on to become a surprise box office success, grossing more than $69 million. After several flops and a moderate success with the comedy Dragnet (1987), Hanks' stature in the film industry rose. The broad success with the fantasy-comedy Quisiera ser grande (1988) established him as a major Hollywood talent, both as a box office draw and within the film industry as an actor. For his performance in the film, Hanks earned his first Academy Award nomination as Best Actor.\n" +
                            "\n" +
                            "Hanks climbed back to the top again with his portrayal of a washed-up baseball legend turned manager in A League of Their Own (1992). Hanks has stated that his acting in earlier roles was not great, but that he subsequently improved. In an interview with Vanity Fair, Hanks noted his \"modern era of movie making ... because enough self-discovery has gone on ... My work has become less pretentiously fake and over the top\". This \"modern era\" began for Hanks, first with Sintonía de amor (1993) and then with Filadelfia (1993). The former was a blockbuster success about a widower who finds true love over the radio airwaves. Richard Schickel of Time magazine called his performance \"charming\", and most critics agreed that Hanks' portrayal ensured him a place among the premier romantic-comedy stars of his generation.\n" +
                            "\n" +
                            "In Philadelphia, he played a gay lawyer with AIDS who sues his firm for discrimination. Hanks lost 35 pounds and thinned his hair in order to appear sickly for the role. In a review for People, Leah Rozen stated, \"Above all, credit for Philadelphia's success belongs to Hanks, who makes sure that he plays a character, not a saint. He is flat-out terrific, giving a deeply felt, carefully nuanced performance that deserves an Oscar.\" Hanks won the 1993 Academy Award for Best Actor for his role in Philadelphia. During his acceptance speech, he revealed that his high school drama teacher Rawley Farnsworth and former classmate John Gilkerson, two people with whom he was close, were gay.\n" +
                            "\n" +
                            "Hanks followed Philadelphia with the blockbuster Forrest Gump (1994) which grossed a worldwide total of over $600 million at the box office. Hanks remarked: \"When I read the script for Gump, I saw it as one of those kind of grand, hopeful movies that the audience can go to and feel ... some hope for their lot and their position in life ... I got that from the movies a hundred million times when I was a kid. I still do.\" Hanks won his second Best Actor Academy Award for his role in Forrest Gump, becoming only the second actor to have accomplished the feat of winning consecutive Best Actor Oscars.\n" +
                            "\n" +
                            "Hanks' next role - astronaut and commander Jim Lovell, in the docudrama Apolo 13 (1995) - reunited him with Ron Howard. Critics generally applauded the film and the performances of the entire cast, which included actors Kevin Bacon, Bill Paxton, Gary Sinise, Ed Harris, and Kathleen Quinlan. The movie also earned nine Academy Award nominations, winning two. Later that year, Hanks starred in Disney/Pixar's computer-animated film Toy Story (1995), as the voice of Sheriff Woody. A year later, he made his directing debut with the musical comedy ¡Eso que tú haces! (1996) about the rise and fall of a 1960s pop group, also playing the role of a music producer.",

                    "Willard Carroll \"Will\" Smith, Jr. (born September 25, 1968) is an American actor, comedian, producer, rapper, and songwriter. He has enjoyed success in television, film, and music. In April 2007, Newsweek called him \"the most powerful actor in Hollywood\". Smith has been nominated for five Golden Globe Awards, two Academy Awards, and has won four Grammy Awards.\n" +
                            "\n" +
                            "In the late 1980s, Smith achieved modest fame as a rapper under the name The Fresh Prince. In 1990, his popularity increased dramatically when he starred in the popular television series The Fresh Prince of Bel-Air. The show ran for six seasons (1990-96) on NBC and has been syndicated consistently on various networks since then. After the series ended, Smith moved from television to film, and ultimately starred in numerous blockbuster films. He is the only actor to have eight consecutive films gross over $100 million in the domestic box office, eleven consecutive films gross over $150 million internationally, and eight consecutive films in which he starred open at the number one spot in the domestic box office tally.\n" +
                            "\n" +
                            "Smith is ranked as the most bankable star worldwide by Forbes. As of 2014, 17 of the 21 films in which he has had leading roles have accumulated worldwide gross earnings of over $100 million each, five taking in over $500 million each in global box office receipts. As of 2014, his films have grossed $6.6 billion at the global box office. He has received Best Actor Oscar nominations for Ali and The Pursuit of Happyness.\n" +
                            "\n" +
                            "Smith was born in West Philadelphia, the son of Caroline (Bright), a Philadelphia school board administrator, and Willard Carroll Smith, Sr., a refrigeration engineer. He grew up in West Philadelphia's Wynnefield neighborhood, and was raised Baptist. He has three siblings, sister Pamela, who is four years older, and twins Harry and Ellen, who are three years younger. Smith attended Our Lady of Lourdes, a private Catholic elementary school in Philadelphia. His parents separated when he was 13, but did not actually divorce until around 2000.\n" +
                            "\n" +
                            "Smith attended Overbrook High School. Though widely reported, it is untrue that Smith turned down a scholarship to attend the Massachusetts Institute of Technology (MIT); he never applied to college because he \"wanted to rap.\" Smith says he was admitted to a \"pre-engineering [summer] program\" at MIT for high school students, but he did not attend. According to Smith, \"My mother, who worked for the School Board of Philadelphia, had a friend who was the admissions officer at MIT. I had pretty high SAT scores and they needed black kids, so I probably could have gotten in. But I had no intention of going to college.\"\n" +
                            "\n" +
                            "Smith started as the MC of the hip-hop duo DJ Jazzy Jeff & The Fresh Prince, with his childhood friend Jeffrey \"DJ Jazzy Jeff\" Townes as producer, as well as Ready Rock C (Clarence Holmes) as the human beat box. The trio was known for performing humorous, radio-friendly songs, most notably \"Parents Just Don't Understand\" and \"Summertime\". They gained critical acclaim and won the first Grammy awarded in the Rap category (1988).\n" +
                            "\n" +
                            "Smith spent money freely around 1988 and 1989 and underpaid his income taxes. The Internal Revenue Service eventually assessed a $2.8 million tax debt against Smith, took many of his possessions, and garnished his income. Smith was nearly bankrupt in 1990, when the NBC television network signed him to a contract and built a sitcom, The Fresh Prince of Bel-Air, around him.\n" +
                            "\n" +
                            "The show was successful and began his acting career. Smith set for himself the goal of becoming \"the biggest movie star in the world\", studying box office successes' common characteristics.\n" +
                            "\n" +
                            "Smith's first major roles were in the drama Six Degrees of Separation (1993) and the action film Bad Boys (1995) in which he starred opposite Martin Lawrence.\n" +
                            "\n" +
                            "In 1996, Smith starred as part of an ensemble cast in Roland Emmerich's Independence Day. The film was a massive blockbuster, becoming the second highest grossing film in history at the time and establishing Smith as a prime box office draw. He later struck gold again in the summer of 1997 alongside Tommy Lee Jones in the summer hit Men in Black playing Agent J. In 1998, Smith starred with Gene Hackman in Enemy of the State.\n" +
                            "\n" +
                            "He turned down the role of Neo in The Matrix in favor of Wild Wild West (1999). Despite the disappointment of Wild Wild West, Smith has said that he harbors no regrets about his decision, asserting that Keanu Reeves's performance as Neo was superior to what Smith himself would have achieved, although in interviews subsequent to the release of Wild Wild West he stated that he \"made a mistake on Wild Wild West. That could have been better.\"\n" +
                            "\n" +
                            "In 2005, Smith was entered into the Guinness Book of World Records for attending three premieres in a 24-hour time span.\n" +
                            "\n" +
                            "He has planned to star in a feature film remake of the television series It Takes a Thief.\n" +
                            "\n" +
                            "On December 10, 2007, Smith was honored at Grauman's Chinese Theatre on Hollywood Boulevard. Smith left an imprint of his hands and feet outside the world-renowned theater in front of many fans. Later that month, Smith starred in the film I Am Legend, released December 14, 2007. Despite marginally positive reviews, its opening was the largest ever for a film released in the United States during December. Smith himself has said that he considers the film to be \"aggressively unique\". A reviewer said that the film's commercial success \"cemented [Smith's] standing as the number one box office draw in Hollywood.\" On December 1, 2008, TV Guide reported that Smith was selected as one of America's top ten most fascinating people of 2008 for a Barbara Walters ABC special that aired on December 4, 2008.\n" +
                            "\n" +
                            "In 2008 Smith was reported to be developing a film entitled The Last Pharaoh, in which he would be starring as Taharqa. It was in 2008 that Smith starred in the superhero movie Hancock.\n" +
                            "\n" +
                            "Men in Black III opened on May 25, 2012 with Smith again reprising his role as Agent J. This was his first major starring role in four years.\n" +
                            "\n" +
                            "On August 19, 2011, it was announced that Smith had returned to the studio with producer La Mar Edwards to work on his fifth studio album. Edwards has worked with artists such as T.I., Chris Brown, and Game. Smith's most recent studio album, Lost and Found, was released in 2005.\n" +
                            "\n" +
                            "Smith and his son Jaden played father and son in two productions: the 2006 biographical drama The Pursuit of Happyness, and the science fiction film After Earth, which was released on May 31, 2013.\n" +
                            "\n" +
                            "Smith starred opposite Margot Robbie in the romance drama Focus. He played Nicky Spurgeon, a veteran con artist who takes a young, attractive woman under his wing. Focus was released on February 27, 2015. Smith was set to star in the Sci-Fic thriller Brilliance, an adaptation of Marcus Sakey's novel of the same name scripted by Jurassic Park writer David Koepp. But he left the project.\n" +
                            "\n" +
                            "Smith played Dr. Bennet Omalu of the Brain Injury Research Institute in the sports-drama Concussion, who became the first person to discover chronic traumatic encephalopathy (CTE) in a football player's brain. CTE is a degenerative disease caused by severe trauma to the head that can be discovered only after death. Smith's involvement is mostly due to his last-minute exit from the Sci-Fi thriller-drama Brilliance. Concussion was directed by Peter Landesman and-bead filmed in Pittsburgh, according to the Pittsburgh Tribune-Review. It received $14.4 million in film tax credits from Pennsylvania. Principal photography started on October 27, 2014. Actress Gugu Mbatha-Raw played his wife. Omalu served as a consultant.\n" +
                            "\n" +
                            "As of November 2015, Smith is set to star in the independent drama Collateral Beauty, which will be directed by David Frankel. Smith will play a New York advertising executive who succumbs to an deep depression after a personal tragedy.\n" +
                            "\n" +
                            "Nobel Peace Prize Concert December 11, 2009, in Oslo, Norway: Smith with wife Jada and children Jaden and Willow Smith married Sheree Zampino in 1992. They had one son, Trey Smith, born on November 11, 1992, and divorced in 1995. Trey appeared in his father's music video for the 1998 single \"Just the Two of Us\". He also acted in two episodes of the sitcom All of Us, and has appeared on The Oprah Winfrey Show and on the David Blaine: Real or Magic TV special.\n" +
                            "\n" +
                            "Smith married actress Jada Koren Pinkett in 1997. Together they have two children: Jaden Christopher Syre Smith (born 1998), his co-star in The Pursuit of Happyness and After Earth, and Willow Camille Reign Smith (born 2000), who appeared as his daughter in I Am Legend. Smith and his brother Harry own Treyball Development Inc., a Beverly Hills-based company named after Trey. Smith and his family reside in Los Angeles, California.\n" +
                            "\n" +
                            "Smith was consistently listed in Fortune Magazine's \"Richest 40\" list of the forty wealthiest Americans under the age of 40."
            };
            String[] fotos = {
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Rachel_McAdams_3.jpg/751px-Rachel_McAdams_3.jpg",
                    "https://upload.wikimedia.org/wikipedia/commons/d/d9/Kate_Beckinsale_2011_Comic-Con_%28truer_color%29.jpg",
                    "https://upload.wikimedia.org/wikipedia/commons/f/fb/Tom_Hanks_2016.jpg",
                    "https://upload.wikimedia.org/wikipedia/commons/6/65/Will_Smith_by_Gage_Skidmore.jpg"

            };
            for (int i = 0; i < 4; i++) {
                Artista artista = new Artista(i + 1, nombre[i], apellido[i], nacimiento[i], lugares[i], estaturas[i], notas[i], i + 1, fotos[i]);
                artistaAdapter.add(artista);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //Metodos de la interface OnItemClickListener
    @Override
    public void onItemClcik(Artista artista) {

    }

    @Override
    public void onLongItemClick(Artista artista) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== RESULT_OK && requestCode==1){

        }
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Intent intent= new Intent(TopActivity.this,AddArtistActivity.class);
        intent.putExtra(Artista.ORDES,artistaAdapter.getItemCount()+1);
        startActivityForResult(intent,1);
    }
}
