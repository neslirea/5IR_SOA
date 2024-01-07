$(function() {

    const endpointUser = "http://127.0.0.1:8088";
    const endpointMission = "http://127.0.0.1:8089";
    const endpointReview = "http://127.0.0.1:8090";
    const endpointOrchestration = "http://127.0.0.1:8091";

    // Errors occuring in modal
    function displayModalError(reason) {
        let msg = "Une erreur est survenue.";

        switch (reason) {
            case "server":
                msg = "Une erreur est survenue avec le serveur.";
                break;

            case "name":
                msg = "Le nom et le prénom doivent être valides.";
                break;
            
            case "missionDetails":
                msg = "Le demandeur, le titre et la description doivent être valides";
                break;

            case "reviewDetails":
                msg = "Veuillez choisir une mission à évaluer.";
                break;

            default:
                break;
        }

        $("#errModal").show();
        if (!document.getElementById(`errModal_${reason}`)) {
            $("#errModal").append(`<div id='errModal_${reason}' class='alert alert-danger' style='display: none;'>${msg}</div>`)
            $(`#errModal_${reason}`).fadeIn();
        }
        
    }

    // Errors occuring in body
    function displayBodyError(reason) {
        let msg = "Une erreur est survenue";

        switch (reason) {
            case "getInit":
                msg = "Impossible de récupérer la liste des bénéficiaires.";
                break;

            case "getVal":
                msg = "Impossible de récupérer la liste des superviseurs.";
                break;

            case "getVol":
                msg = "Impossible de récupérer la liste des bénévoles.";
                break;

            case "getMissions":
                msg = "Impossible de récupérer la liste des missions.";
                break;

            case "getReviews":
                msg = "Impossible de récupérer la liste des avis.";
                break;

            default:
                break;
        }

        $("#errBody").show();
        if (!document.getElementById(`errBody_${reason}`)) {
            $("#errBody").append(`<div id='errBody_${reason}' class='alert alert-danger' style='display: none;'>${msg}</div>`)
            $(`#errBody_${reason}`).fadeIn();
        }
    }

    // Sucesses occuring in modal
    function displayModalSuccess(reason) {
        let msg = "Opération effectuée.";

        switch (reason) {
            case "addUser":
                msg = "L'utilisateur a été ajouté. Vous pouvez fermer cette boîte de dialogue.";
                break;

            case "addMission":
                msg = "La demande a été envoyée. Vous pouvez fermer cette boîte de dialogue.";
                break;
            
            case "addReview":
                msg = "Votre avis a été envoyé. Vous pouvez fermer cette boîte de dialogue.";
                break;

            default:
                break;
        }

        $("#errModal").show();
        if (!document.getElementById(`errModal_${reason}`)) {
            $("#errModal").append(`<div id='errModal_${reason}' class='alert alert-success' style='display: none;'>${msg}</div>`)
            $(`#errModal_${reason}`).fadeIn();
        }
        
    }


    if (document.title == "Utilisateurs - Plateforme d'aide") {

        let initiatorList = null;
        let volunteerList = null;
        let validatorList = null;
        let initOff = false;
        let volOff = false;
        let valOff = false;

        // Look at how many requests are solved to start loading content
        let fetchCount = 0;
        function fetchCallback() {
            fetchCount++;
            
            if (fetchCount == 3) {
                fetchCount = 0;
                fillUserTable(initiatorList, volunteerList, validatorList, initOff, volOff, valOff);
                fillValidatorSelection(validatorList);
            }
        }

        function fillUserTable(initiators, volunteers, validators, initOff, volOff, valOff) {
            const tableBody = $("#userTableBody");
            tableBody.hide();
            tableBody.empty();

            if (initiators && !initOff) {
                for (let init of initiators) {
                    const infos = `<tr>
                                        <td>${init.id}</td>
                                        <td>${init.firstName}</td>
                                        <td>${init.lastName}</td>
                                        <td>Bénéficiaire</td>
                                        <td>${init.validator==null?'-':init.validator.firstName+" "+init.validator.lastName}</td>
                                    </tr>`;
                    tableBody.append(infos);
                }
            }

            if (volunteers && !volOff) {
                for (let vol of volunteers) {
                    const infos = `<tr>$
                                        <td>${vol.id}</td>
                                        <td>${vol.firstName}</td>
                                        <td>${vol.lastName}</td>
                                        <td>Bénévole</td>
                                        <td>-</td>
                                    </tr>`;
                    tableBody.append(infos);
                }
            }

            if (validators && !valOff) {
                for (let val of validators) {
                    const infos = `<tr>$
                                        <td>${val.id}</td>
                                        <td>${val.firstName}</td>
                                        <td>${val.lastName}</td>
                                        <td>Superviseur</td>
                                        <td>-</td>
                                    </tr>`;
                    tableBody.append(infos);
                }
            }

            if (initiators || volunteers || validators) {
                tableBody.fadeIn();
            } else {
                $("#noUserFound").fadeIn();
            }
        }

        function fillValidatorSelection(validators) {
            if (validators) {
                const valSelect = $("#validatorSelection");
                valSelect.empty();
                valSelect.append("<option value='default'>Choisir un superviseur...</option>");

                for (let val of validators) {
                    valSelect.append(`<option value="${val.id}">${val.firstName} ${val.lastName}</option>`);
                }
            }
        }

        function initCallbacks() {
            const initiatorBtn = $("#toggleInitiators");
            const volunteerBtn = $("#toggleVolunteers");
            const validatorBtn = $("#toggleValidators");

            initiatorBtn.click(function (e) { 
                initiatorBtn.toggleClass("active");
                initiatorBtn.toggleClass("btn-success");
                initiatorBtn.toggleClass("btn-light");
                if (initiatorBtn.hasClass("active")) {
                    initOff = false;
                    fillUserTable(initiatorList, volunteerList, validatorList, initOff, volOff, valOff);
                }
                else {
                    initOff = true;
                    fillUserTable(initiatorList, volunteerList, validatorList, initOff, volOff, valOff);
                }
            });

            volunteerBtn.click(function (e) { 
                volunteerBtn.toggleClass("active");
                volunteerBtn.toggleClass("btn-success");
                volunteerBtn.toggleClass("btn-light");
                if (volunteerBtn.hasClass("active")) {
                    volOff = false;
                    fillUserTable(initiatorList, volunteerList, validatorList, initOff, volOff, valOff);
                }
                else {
                    volOff = true;
                    fillUserTable(initiatorList, volunteerList, validatorList, initOff, volOff, valOff);
                }
            });

            validatorBtn.click(function (e) { 
                validatorBtn.toggleClass("active");
                validatorBtn.toggleClass("btn-success");
                validatorBtn.toggleClass("btn-light");
                if (validatorBtn.hasClass("active")) {
                    valOff = false;
                    fillUserTable(initiatorList, volunteerList, validatorList, initOff, volOff, valOff);
                }
                else {
                    valOff = true;
                    fillUserTable(initiatorList, volunteerList, validatorList, initOff, volOff, valOff);
                }
            });

            $("#addUserBtn").click(function (e) {  
                const modal = new bootstrap.Modal(document.getElementById("addUserModal"));
                $("#errModal").empty();
                $("#errModal").hide();
                modal.show();
            });


            const userTypeSel = $("#userTypeSelection");
            $("#submitUserBtn").click(function (e) { 
                $("#errModal").empty();
                const invalidFirstName = $("#firstName").val() == "" || $("#firstName").val().match(/[^\p{L}]/gu) != null;
                const invalidLastName = $("#lastName").val() == "" || $("#lastName").val().match(/[^\p{L}]/gu) != null;
                if (invalidFirstName || invalidLastName) {
                    displayModalError("name");
                } else {
                    let userType = "";
                    const userData = {
                        "lastName":$("#lastName").val(),
                        "firstName":$("#firstName").val()
                    };
                    
                    if (userTypeSel.val() == "validator") {
                        userType = "Validator";
                    } else if (userTypeSel.val() == "initiator") {
                        userType = "Initiator";
                        if ($("#validatorSelection").val() != "default") {userData.validator = parseInt($("#validatorSelection").val());}
                        // userData.validator = $("#validatorSelection").val() == "default" ? -1 : parseInt($("#validatorSelection").val());
                    } else if (userTypeSel.val() == "volunteer") {
                        userType = "Volunteer";
                    }

                    $.ajax({
                        url: endpointUser + "/add" + userType,
                        type: "POST",
                        data: JSON.stringify(userData),
                        contentType: "application/json; charset=utf-8",
                        success: function (data, textStatus, jqXHR) {
                            displayModalSuccess("addUser");
                            loadUserData();
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            displayModalError("server");
                        }
                    });
                }
            });

            userTypeSel.change(function (e) { 
                if (userTypeSel.val() != "initiator") {
                    $("#validatorSelectionDiv").hide();
                } else {
                    $("#validatorSelectionDiv").show();
                }
            });
        }

        function loadUserData() {
            // Fetch list of all initiators
            $.ajax({
                url: endpointOrchestration + "/initiator",
                type: "GET",
                success: function (data, textStatus, jqXHR) {
                    initiatorList = data;
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    displayBodyError("getInit");
                },
                complete: function(jqXHR, textStatus) {
                    fetchCallback();
                }
            });

            // Fetch list of all volunteers
            $.ajax({
                url: endpointUser + "/volunteer",
                type: "GET",
                success: function (data, textStatus, jqXHR) {
                    volunteerList = data;
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    displayBodyError("getVol");
                },
                complete: function(jqXHR, textStatus) {
                    fetchCallback();
                }
            });

            // Fetch list of all validators
            $.ajax({
                url: endpointUser + "/validator",
                type: "GET",
                success: function (data, textStatus, jqXHR) {
                    validatorList = data;
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    displayBodyError("getVal");
                },
                complete: function(jqXHR, textStatus) {
                    fetchCallback();
                }
            });
        }


        // Fetch data for the table
        loadUserData();

        // init value of elements
        $("#userTypeSelection").val("initiator");
    
        // init events
        initCallbacks();

        // show content
        $("#titleDiv").fadeIn();
        $("#addDiv").fadeIn();
        $("#tableDiv").fadeIn();

    }


    if (document.title == "Missions - Plateforme d'aide") {
        
        let initiatorList = null;
        let missionList = null;

        // Look at how many requests are solved to start loading content
        let fetchCount = 0;
        function fetchCallback() {
            fetchCount++;

            if (fetchCount == 2) {
                fetchCount = 0;
                fillMissionTables(missionList, $("#filterSelection").val());
                fillInitiatorSelection(initiatorList);
            }
        }

        function fillMissionTables(missions, filter) {
            const inProgressBody = $("#missionTableBodyInProgress");
            const pendingBody = $("#missionTableBodyPending");
            const completedBody = $("#missionTableBodyCompleted");

            inProgressBody.hide();
            pendingBody.hide();
            completedBody.hide();

            inProgressBody.empty();
            pendingBody.empty();
            completedBody.empty();

            if (missions) {
                for (let mission of missions) {
                    if (filter == "all" || parseInt(filter) == mission.initiator.id) {

                        if (mission.status == "InProgress") {
                            const infos = `<tr>
                                                <td>${mission.id}</td>
                                                <td>${mission.creationDate}</td>
                                                <td>${mission.titre}</td>
                                                <td>${mission.description}</td>
                                                <td>${mission.initiator.firstName+" "+mission.initiator.lastName}</td>
                                                <td>${mission.volunteer==null?'-':mission.volunteer.firstName+" "+mission.volunteer.lastName}</td>
                                            </tr>`;

                            inProgressBody.append(infos);

                        } else if (mission.status == "Finished") {
                            const infos = `<tr>
                                                <td>${mission.id}</td>
                                                <td>${mission.creationDate}</td>
                                                <td>${mission.creationDate}</td>
                                                <td>${mission.titre}</td>
                                                <td>${mission.description}</td>
                                                <td>${mission.initiator.firstName+" "+mission.initiator.lastName}</td>
                                                <td>${mission.volunteer==null?'-':mission.volunteer.firstName+" "+mission.volunteer.lastName}</td>
                                            </tr>`;

                            completedBody.append(infos);
                        
                        } else {
                            const infos = `<tr>
                                                <td>${mission.id}</td>
                                                <td>${mission.creationDate}</td>
                                                <td>${mission.status}</td>
                                                <td>${mission.titre}</td>
                                                <td>${mission.description}</td>
                                                <td>${mission.initiator.firstName+" "+mission.initiator.lastName}</td>
                                                <td>${mission.volunteer==null?'-':mission.volunteer.firstName+" "+mission.volunteer.lastName}</td>
                                            </tr>`;

                            pendingBody.append(infos);
                        }
                    }
                }
            }

                if (inProgressBody.html()) {
                    inProgressBody.fadeIn();
                    $("#noMissionFoundInProgress").hide();
                } else {
                    $("#noMissionFoundInProgress").fadeIn();
                }

                if (pendingBody.html()) {
                    pendingBody.fadeIn();
                    $("#noMissionFoundPending").hide();
                } else {
                    $("#noMissionFoundPending").fadeIn();
                }

                if (completedBody.html()) {
                    completedBody.fadeIn();
                    $("#noMissionFoundCompleted").hide();
                } else {
                    $("#noMissionFoundCompleted").fadeIn();
                }
        }

        function fillInitiatorSelection(initiators) {
            if (initiators) {
                const initSelectModal = $("#initiatorSelection");
                const initFilter = $("#filterSelection");

                initSelectModal.empty();
                initFilter.empty()

                initSelectModal.append("<option value='default'>Choisir un bénéficiaire...</option>");
                initFilter.append("<option value='all'>Tous</option>");

                for (let init of initiators) {
                    initSelectModal.append(`<option value="${init.id}">${init.firstName} ${init.lastName}</option>`);
                    initFilter.append(`<option value="${init.id}">${init.firstName} ${init.lastName}</option>`);
                }
            }
        }

        function loadMissionsData() {
            // Fetch list of all initiators
            $.ajax({
                url: endpointUser + "/initiator",
                type: "GET",
                success: function (data, textStatus, jqXHR) {
                    initiatorList = data;
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    displayBodyError("getInit");
                },
                complete: function(jqXHR, textStatus) {
                    fetchCallback();
                }
            });

            // Fetch list of all missions
            $.ajax({
                url: endpointOrchestration + "/mission",
                type: "GET",
                success: function (data, textStatus, jqXHR) {
                    missionList = data;
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    displayBodyError("getMissions");
                },
                complete: function(jqXHR, textStatus) {
                    fetchCallback();
                }
            });
        }

        function hasValidator(initId) {
            for (let init of initiatorList) {
                if (init.id == initId && init.validator != null) {
                    return true;
                }
            }
            return false;
        }

        function initCallbacks() {
            $("#addMissionBtn").click(function (e) {  
                const modal = new bootstrap.Modal(document.getElementById("addMissionModal"));
                $("#errModal").empty();
                $("#errModal").hide();
                modal.show();
            });

            $("#filterSelection").change(function (e) { 
                fillMissionTables(missionList, $("#filterSelection").val());
            });


            const toggleIPBtn = $("#toggleInProgress");
            const togglePendBtn = $("#togglePending");
            const toggleCompBtn = $("#toggleCompleted");
            toggleIPBtn.click(function (e) { 
                const table = $("#missionTableInProgress");
                if (table.is(":visible")) {
                    table.hide();
                    toggleIPBtn.text("➕");
                } else {
                    table.show();
                    toggleIPBtn.text("➖");
                }
                
            });
            togglePendBtn.click(function (e) { 
                const table = $("#missionTablePending");
                if (table.is(":visible")) {
                    table.hide();
                    togglePendBtn.text("➕");
                } else {
                    table.show();
                    togglePendBtn.text("➖");
                }
            });
            toggleCompBtn.click(function (e) { 
                const table = $("#missionTableCompleted");
                if (table.is(":visible")) {
                    table.hide();
                    toggleCompBtn.text("➕");
                } else {
                    table.show();
                    toggleCompBtn.text("➖");
                }
            });


            $("#submitMissionBtn").click(function (e) { 
                $("#errModal").empty();

                if ($("#title").val() && $("#description").val() && $("#initiatorSelection").val() != "default") {
                    const date = new Date();
                    const formattedDate = `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()} ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;                    
                    const missionData = {
                        "titre":$("#title").val(),
                        "description":$("#description").val(),
                        "creationDate": formattedDate,
                        "initiator":parseInt($("#initiatorSelection").val()),
                        "status":hasValidator(parseInt($("#initiatorSelection").val())) ? "WaitingForValidator" : "WaitingForVolunteer"
                    };

                    $.ajax({
                        url: endpointMission + "/mission",
                        type: "POST",
                        data: JSON.stringify(missionData),
                        contentType: "application/json; charset=utf-8",
                        success: function (data, textStatus, jqXHR) {
                            displayModalSuccess("addMission");
                            loadMissionsData();
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            displayModalError("server");
                        }
                    });

                } else {
                    displayModalError("missionDetails");
                } 
            });
        }

        // Fetch data for the table
        loadMissionsData();
    
        // init events
        initCallbacks();

        // show content
        $("#titleDiv").fadeIn();
        $("#addDiv").fadeIn();
        $("#missionTablesDiv").fadeIn();
    }


    if (document.title == "Avis - Plateforme d'aide") {

        let reviewList = null;
        let missionList = null;

        // Look at how many requests are solved to start loading content
        let fetchCount = 0;
        function fetchCallback() {
            fetchCount++;

            if (fetchCount == 2) {
                fetchCount = 0;
                fillReviewTable(reviewList);
                fillMissionSelection(missionList);
            }
        }

        function loadReviewData() {
            // Fetch list of all reviews
            $.ajax({
                url: endpointOrchestration + "/review",
                type: "GET",
                success: function (data, textStatus, jqXHR) {
                    reviewList = data;
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    displayBodyError("getReviews");
                },
                complete: function(jqXHR, textStatus) {
                    fetchCallback();
                }
            });

            // Fetch list of all missions
            $.ajax({
                url: endpointMission + "/mission",
                type: "GET",
                success: function (data, textStatus, jqXHR) {
                    missionList = data;
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    displayBodyError("getMissions");
                },
                complete: function(jqXHR, textStatus) {
                    fetchCallback();
                }
            });
        }

        function fillReviewTable(reviews) {
            const tableBody = $("#reviewTableBody");
            tableBody.hide();
            tableBody.empty();

            if (reviews) {
                for (let review of reviews) {
                    const infos = `<tr>
                                        <td>${review.id}</td>
                                        <td>${review.mission.titre}</td>
                                        <td>${review.note}/5</td>
                                        <td>${review.description}</td>
                                    </tr>`;

                    tableBody.append(infos);
                }

                tableBody.fadeIn();

            } else {
                $("#noReviewFound").fadeIn();
            }
        }

        function fillMissionSelection(missions) {
            if (missions) {
                const missionSelect = $("#missionSelection");
                missionSelect.empty();
                missionSelect.append("<option value='default'>Choisir une mission...</option>");

                for (let mission of missions) {
                    missionSelect.append(`<option value="${mission.id}">${mission.titre}</option>`);
                }
            }
        }

        function initCallbacks() {
            $("#addReviewBtn").click(function (e) {  
                const modal = new bootstrap.Modal(document.getElementById("addReviewModal"));
                $("#errModal").empty();
                $("#errModal").hide();
                modal.show();
            });

            $("#rating").change(function (e) { 
                console.log("okokok");
                $("#ratingLabel").text(`Note - ${$("#rating").val()}/5`);
            });

            $("#submitReviewBtn").click(function (e) { 
                $("#errModal").empty();

                if ($("#missionSelection").val() != "default") {
                    const reviewData = {
                        "note":parseInt($("#rating").val()),
                        "mission":parseInt($("#missionSelection").val()),
                        "description":$("#comment").val()
                    };

                    $.ajax({
                        url: endpointReview + "/review",
                        type: "POST",
                        data: JSON.stringify(reviewData),
                        contentType: "application/json; charset=utf-8",
                        success: function (data, textStatus, jqXHR) {
                            displayModalSuccess("addReview");
                            loadReviewData();
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            displayModalError("server");
                        }
                    });

                } else {
                    displayModalError("reviewDetails");
                } 
            });
        }

        // Fetch data for the table
        loadReviewData();

        // init value of elements
        $("#rating").val("3");
    
        // init events
        initCallbacks();

        // show content
        $("#titleDiv").fadeIn();
        $("#addDiv").fadeIn();
        $("#reviewTableDiv").fadeIn();
    }


});
