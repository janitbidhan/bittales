$(document).ready(function() {
  var spanishUrl = "http://localhost:8081/i18n/spanish";
  var englishUrl = "http://localhost:8081/i18n/english";

  // Fetch the Spanish data
  $.getJSON(spanishUrl, function(spanishData) {
    // Fetch the English data
    $.getJSON(englishUrl, function(englishData) {
      // Create a table to display the results
      var table = $("<table>").addClass("table table-striped");
      var headerRow = $("<tr>").appendTo(table);
      $("<th>").text("Key").appendTo(headerRow);
      $("<th>").text("Spanish").appendTo(headerRow);
      $("<th>").text("English").appendTo(headerRow);
      $("<th>").text("").appendTo(headerRow);
      $("<th>").text("").appendTo(headerRow);

      // Loop through each Spanish record and find the matching English record
      $.each(spanishData, function(i, spanishRecord) {
        var englishRecord = findEnglishRecord(englishData, spanishRecord.lkey);
        var row = $("<tr>").appendTo(table);
        $("<td>").text(spanishRecord.lkey).appendTo(row);
        $("<td>").html("<input type='text' class='form-control' value='" + spanishRecord.lvalue + "'>").appendTo(row);
        $("<td>").html("<input type='text' class='form-control' value='" + englishRecord.lvalue + "'>").appendTo(row);
        $("<td>").html("<button class='btn btn-primary save-btn'>Save</button>").appendTo(row);
        $("<td>").html("<button class='btn btn-danger delete-btn'>Delete</button>").appendTo(row);
      });

      // Add the table to the page
      table.appendTo($("#result"));

      // Add event listeners for the add and remove buttons
      $("#add-row-btn").click(function() {
        var newRow = $("<tr>").appendTo(table);
        $("<td>").html("<input type='text' class='form-control'>").appendTo(newRow);
        $("<td>").html("<input type='text' class='form-control'>").appendTo(newRow);
        $("<td>").html("<input type='text' class='form-control'>").appendTo(newRow);
        $("<td>").html("<button class='btn btn-primary save-btn'>Save</button>").appendTo(newRow);
        $("<td>").html("<button class='btn btn-danger delete-btn'>Delete</button>").appendTo(newRow);
      });

      table.on("click", ".delete-btn", function() {
        $(this).closest("tr").remove();
      });

      table.on("click", ".save-btn", function() {
        var row = $(this).closest("tr");
        var key = row.find("td:first-child").text();
        var spanishValue = row.find("td:nth-child(2) input").val();
        var englishValue = row.find("td:nth-child(3) input").val();

        // Perform AJAX requests to update the server data
        $.ajax({
          type: "PUT",
          url: spanishUrl,
          contentType: "application/json",
          data: JSON.stringify({ lkey: key, lvalue: spanishValue }),
          success: function() {
            console.log("Spanish record updated successfully.");
          }
        });

        $.ajax({
          type: "PUT",
          url: englishUrl,
          contentType: "application/json",
          data: JSON.stringify({ lkey: key, lvalue: englishValue }),
          success: function() {
            console.log("English record updated successfully.");
          }
        });
      });
    });
  });
  // Helper function to find an English record with a given key
  function findEnglishRecord(englishData, key) {
    for (var i = 0; i < englishData.length; i++) {
      if (englishData[i].lkey === key) {
        return englishData[i];
      }
    }
    return null;
  }
});
