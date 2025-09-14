package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/go-vgo/robotgo"
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
	robotgo.KeyTap("shift", "tab")
}

func goEnter(c *gin.Context) {
	robotgo.KeyTap("enter")
}

func goEscape(c *gin.Context) {
	robotgo.KeyTap("escape")
}

func main() {

	// Initialize default route
	router := gin.Default()

	// Link path with handler functions
	router.GET("/list/services", listServices)
	router.GET("/list/service/:id", getServiceById)
	router.GET("/navigation/next", goNext)
	router.GET("/navigation/previous", goPrevious)
	router.GET("navigation/enter", goEnter)
	router.GET("navigation/escape", goEscape)

	// Attach the router to an http server and start the server
	router.Run("localhost:8080")
}
