package main

import (
	"net/http"
	"os"

	"github.com/gin-gonic/gin"
	"github.com/go-vgo/robotgo"
	"github.com/joho/godotenv"
)

// Services
type service struct {
	ID      string `json:"id"`
	Name    string `json:"name"`
	Website string `json:"website"`
}

// Initialize with some basic services, more can be add using the proper endpoint
var services = []service{
	{ID: "0", Name: "Netflix", Website: "https://www.netflix.com"},
	{ID: "1", Name: "Youtube", Website: "https://www.youtube.com/"},
	{ID: "2", Name: "Crunchyroll", Website: "https://www.crunchyroll.com/"},
	{ID: "3", Name: "Disney+", Website: "https://www.disneyplus.com/fr-fr"},
}

// CORS Handler
func CorsMiddleware() gin.HandlerFunc {
	return func(c *gin.Context) {
		c.Writer.Header().Set("Access-Control-Allow-Origin", "*")
		c.Writer.Header().Set("Access-Control-Allow-Credentials", "true")
		c.Writer.Header().Set("Access-Control-Allow-Headers", "Content-Type, Content-Length, Accept-Encoding, Authorization")
		c.Writer.Header().Set("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PATCH, DELETE, PUT")

		if c.Request.Method == "OPTIONS" {
			c.AbortWithStatus(http.StatusNoContent)
			return
		}

		c.Next()
	}
}

func listServices(c *gin.Context) {
	c.IndentedJSON(http.StatusOK, services)
}

func getServiceById(c *gin.Context) {
	id := c.Param("id")

	for _, k := range services {
		if k.ID == id {
			runService(c, k)
			return
		}
	}
	c.IndentedJSON(http.StatusNotFound, gin.H{"message": "service not found"})
}

func runService(c *gin.Context, k service) {
	c.IndentedJSON(http.StatusOK, k)
}

func goNext(c *gin.Context) {
	robotgo.KeyTap("tab")
}

func goPrevious(c *gin.Context) {
	robotgo.KeyDown("shift")
	robotgo.KeyTap("tab")
	robotgo.KeyUp("shift")
}

func goEnter(c *gin.Context) {
	robotgo.KeyTap("enter")
}

func goEscape(c *gin.Context) {
	robotgo.KeyTap("escape")
}

func goVolumeUp(c *gin.Context) {
	robotgo.KeyTap("up")
}

func goVolumeDown(c *gin.Context) {
	robotgo.KeyTap("down")
}

func main() {
	//Get env variables
	godotenv.Load()
	var ip string = os.Getenv("IP")
	var port string = os.Getenv("PORT")
	// Initialize default route
	router := gin.Default()

	// Link path with handler functions
	router.Use(CorsMiddleware())
	router.GET("/list/services", listServices)
	router.GET("/list/service/:id", getServiceById)
	router.GET("/navigation/next", goNext)
	router.GET("/navigation/previous", goPrevious)
	router.GET("/navigation/volume/up", goVolumeUp)
	router.GET("/navigation/volume/down", goVolumeDown)
	router.GET("/navigation/enter", goEnter)
	router.GET("/navigation/escape", goEscape)

	// Attach the router to an http server and start the server
	router.Run(ip + ":" + port)
}
