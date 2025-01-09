// File Directory //
package platformer.Services;

// Base Class //
public class ServicesConstants {
    Level[] levelArray = {
            new Level( // Level name, platforms, walls
                    "Tutorial",

                    new int[][] // Platforms //
                    {
                            { 0, 0, 0, 0 },
                            {},
                            {}
                    },

                    new int[][] // Walls //
                    {
                            {}
                    }),

            new Level( // Level name, platforms, walls
                    "Tutorial",

                    new int[][] // Platforms //
                    {
                            {}
                    },

                    new int[][] // Walls //
                    {
                            {}
                    })
    };
}