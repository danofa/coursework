//
// breakout.c
//
// Computer Science 50
// Problem Set 4
//

// standard libraries
#define _XOPEN_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Stanford Portable Library
#include "gevents.h"
#include "gobjects.h"
#include "gwindow.h"

// height and width of game's window in pixels
#define HEIGHT 600
#define WIDTH 400

// number of rows of bricks
#define ROWS 7

// number of columns of bricks
#define COLS 10

// radius of ball in pixels
#define RADIUS 10

// lives
#define LIVES 3

#define PADDLEH 10
#define PADDLEW 60
#define SPACE_UNDER_PADDLE 50
#define PADDLE_Y ((HEIGHT - PADDLEH) - SPACE_UNDER_PADDLE)

// prototypes
void initBricks(GWindow window);
GOval initBall(GWindow window);
GRect initPaddle(GWindow window);
GLabel initScoreboard(GWindow window);
void updateScoreboard(GWindow window, GLabel label, int points);
GObject detectCollision(GWindow window, GOval ball);

int main(void)
{
    // seed pseudorandom number generator
    srand48(time(NULL));

    // instantiate window
    GWindow window = newGWindow(WIDTH, HEIGHT);

    // instantiate bricks
    initBricks(window);

    // instantiate ball, centered in middle of window
    GOval ball = initBall(window);

    // instantiate paddle, centered at bottom of window
    GRect paddle = initPaddle(window);

    // instantiate scoreboard, centered in middle of window, just above ball
    GLabel label = initScoreboard(window);

    // number of bricks initially
    int bricks = COLS * ROWS;

    // number of lives initially
    int lives = LIVES;

    // number of points initially
    int points = 0;

    // velocity
    double velocityx = 0.0;
    double velocityy = 0.0;

    bool started = false;

    // keep playing until game over
    while (lives > 0 && bricks > 0)
    {

        // mouse movement and click events
        GEvent event = getNextEvent(MOUSE_EVENT);
        if (event != NULL)
        {
            if (getEventType(event) == MOUSE_MOVED)
            {
                double x = getX(event);
                if(x - (PADDLEW / 2) < 0)
                    setLocation(paddle,0, PADDLE_Y);
                else if(x + (PADDLEW / 2) > WIDTH)
                    setLocation(paddle, WIDTH - PADDLEW, PADDLE_Y);                
                else
                    setLocation(paddle, x - (PADDLEW / 2), PADDLE_Y);
            }
            else if (getEventType(event) == MOUSE_CLICKED)
            {
                if(!started)
                {
                    printf("game started\n");
                    velocityx = drand48();
                    velocityy = drand48() + 1.0;    
                    velocityy = -velocityy;                           
                    started = true;
                }
            }
        }

        // ball maving code.
        move(ball, velocityx, velocityy);

        if (getX(ball) + getWidth(ball) >= getWidth(window))
        {
            velocityx = -velocityx;
        }
        else if (getX(ball) <= 0)
        {
            velocityx = -velocityx;
        }
        

        if (getY(ball) <= 0)
        {
            velocityy = -velocityy;
        }
        // ball hit bottom
        else if (getY(ball) + getHeight(ball) >= getHeight(window))
        {
            lives--;
            velocityy = 0;
            velocityx = 0;
            removeGWindow(window, ball);
            ball = initBall(window);
            started = false;
        }

        // collision detection of ball and objects
        
        GObject hit = detectCollision(window, ball);
        if(hit != NULL)
        {
            if(hit == paddle)
            {
                // return ball only upwards to avoid trapped ball in paddle                
                if(velocityy > 0)
                {
                    velocityy = -velocityy;
                }
                
            
            } // hit a brick
            else if(strcmp(getType(hit), "GRect") == 0)
            {
                points++;
                updateScoreboard(window, label, points);
                velocityy = -velocityy;
                removeGWindow(window, hit);
                bricks--;

                // random x and y velocity bounce off bricks
                if(velocityy < 0)
                    velocityy = -1.0 - drand48();    
                else
                    velocityy = 1.0 + drand48();    

                if(velocityx < 0)
                    velocityx = -drand48();    
                else
                    velocityx = drand48();    
            }
            
        }

        pause(5);
    }

    // wait for click before exiting
    waitForClick();

    // game over
    closeGWindow(window);
    return 0;
}


/**
 * Initializes window with a grid of bricks.
 */
void initBricks(GWindow window)
{
    #define BRICK_GAP 5
    #define BORDER 2
    
    int BRICK_W = (WIDTH - (BRICK_GAP * (COLS-1)) - (BORDER * 2)) / COLS;
    int BRICK_H = BRICK_W / 3;
    int colour = 0;
    
    for(int r = 0; r < ROWS; r++)
    {
        for(int c = 0; c < COLS; c++)
        {
            GRect rect = newGRect(BORDER + ((c * BRICK_W) + (c * BRICK_GAP)), 
                                  BORDER + ((r * BRICK_H) + (r * BRICK_GAP)), BRICK_W, BRICK_H);
            setFilled(rect, true);
            
            if(colour % 5 == 0)
                setColor(rect, "GREEN");
            else if(colour % 5 == 1)
                setColor(rect, "CYAN");
            else if(colour % 5 == 2)
                setColor(rect, "RED");
            else if(colour % 5 == 3)
                setColor(rect, "YELLOW");
            else if(colour % 5 == 4)
                setColor(rect, "PINK");
            
            add(window, rect);
        }

        colour++;

    }
    
}

/**
 * Instantiates ball in center of window.  Returns ball.
 */
GOval initBall(GWindow window)
{
    #define SIZE 20
    GOval ball = newGOval((WIDTH - SIZE) / 2, (HEIGHT - SIZE) / 2, SIZE, SIZE);
    setColor(ball, "BLACK");
    setFilled(ball, true);
    add(window, ball);
    return ball;
}

/**
 * Instantiates paddle in bottom-middle of window.
 */
GRect initPaddle(GWindow window)
{
    GRect rect = newGRect((WIDTH - PADDLEW) / 2, (HEIGHT - PADDLEH) - SPACE_UNDER_PADDLE, PADDLEW, PADDLEH);
    setFilled(rect, true);
    setColor(rect, "GRAY");
    add(window, rect);
    return rect;
}

/**
 * Instantiates, configures, and returns label for scoreboard.
 */
GLabel initScoreboard(GWindow window)
{
    GLabel label = newGLabel("0");
    setFont(label, "SansSerif-22");
    setColor(label, "GRAY");
    double x = (getWidth(window) - getWidth(label)) / 2;
    double y = (getHeight(window) - getHeight(label)) / 2;
    setLocation(label, x, y);
    add(window, label);
    
    return label;
}

/**
 * Updates scoreboard's label, keeping it centered in window.
 */
void updateScoreboard(GWindow window, GLabel label, int points)
{
    // update label
    char s[12];
    sprintf(s, "%i", points);
    setLabel(label, s);

    // center label in window
    double x = (getWidth(window) - getWidth(label)) / 2;
    double y = (getHeight(window) - getHeight(label)) / 2;
    setLocation(label, x, y);
}

/**
 * Detects whether ball has collided with some object in window
 * by checking the four corners of its bounding box (which are
 * outside the ball's GOval, and so the ball can't collide with
 * itself).  Returns object if so, else NULL.
 */
GObject detectCollision(GWindow window, GOval ball)
{
    // ball's location
    double x = getX(ball);
    double y = getY(ball);

    // for checking for collisions
    GObject object;

    // check for collision at ball's top-left corner
    object = getGObjectAt(window, x, y);
    if (object != NULL)
    {
        return object;
    }

    // check for collision at ball's top-right corner
    object = getGObjectAt(window, x + 2 * RADIUS, y);
    if (object != NULL)
    {
        return object;
    }

    // check for collision at ball's bottom-left corner
    object = getGObjectAt(window, x, y + 2 * RADIUS);
    if (object != NULL)
    {
        return object;
    }

    // check for collision at ball's bottom-right corner
    object = getGObjectAt(window, x + 2 * RADIUS, y + 2 * RADIUS);
    if (object != NULL)
    {
        return object;
    }

    // no collision
    return NULL;
}
