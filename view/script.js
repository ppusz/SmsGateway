$(document).ready(function() {

  var apiRoot = 'http://localhost:8080/api/';
  var contactRowTemplate = $('[contact-row-template]').children()[0];
  var contactsContainer = $('[data-contacts-container]');

  function handleSmsSubmitRequest(event) {
    event.preventDefault();

    var phone = $(this).find('[name="phone-number"]').val();
    var message = $(this).find('[name="message"]').val();

    var requestUrl = apiRoot + 'sms/send';

    $.ajax({
      url: requestUrl,
      method: 'POST',
      processData: false,
      contentType: "application/json; charset=utf-8",
      dataType: 'json',
      data: JSON.stringify({
        phoneNumber: phone,
        message: message
      }),
      complete: function(data) {
        if(data.status === 200) {
          //getAllTasks();
        }
      }
    });
  }
  
  function createElement(data) {
    var element = $(contactRowTemplate).clone();

    element.attr('contact-id', data.id);
    element.find('[contact-name-section] [contact-name-paragraph]').text(data.fullName);
    element.find('[contact-name-section] [contact-name-input]').val(data.fullName);

    element.find('[contact-phone-section] [contact-phone-paragraph]').text(data.phoneNumber);
    element.find('[contact-phone-section] [contact-phone-input]').val(data.phoneNumber);

    return element;
  }

  function handleDatatableRender(data) {
    $('[contacts-block]').toggleClass('contacts-show');
    contactsContainer.empty();
    data.forEach(function(contact) {
      createElement(contact).appendTo(contactsContainer);
    });
  }

  function getAllContacts() {
    var requestUrl = apiRoot + 'contact/getAll';

    $.ajax({
      url: requestUrl,
      method: 'GET',
      success: handleDatatableRender
    });
  }

  function handleContactUpdateRequest() {
    var parentEl = $(this).parent().parent();
    var contacId = parentEl.attr('contact-id');
    var contactName = parentEl.find('[contact-name-input]').val();
    var contactPhone = parentEl.find('[contact-phone-input]').val();
    var requestUrl = apiRoot + 'contact/update';

    $.ajax({
      url: requestUrl,
      method: "PUT",
      processData: false,
      contentType: "application/json; charset=utf-8",
      dataType: 'json',
      data: JSON.stringify({
        id: contacId,
        fullName: contactName,
        phoneNumber: contactPhone
      }),
      success: function(data) {
        parentEl.attr('[contact-id', data.id).toggleClass('contact-row--editing');
        parentEl.find('[contact-name-paragraph]').text(contactName);
        parentEl.find('[contact-phone-paragraph]').text(contactPhone);
      }
    });
  }  

function handleContactDeleteRequest() {
    var parentEl = $(this).parent().parent();
    var contactId = parentEl.attr('contact-id');
    var requestUrl = apiRoot + 'contact/delete';

    $.ajax({
      url: requestUrl + '/?' + $.param({
        id: contactId
      }),
      method: 'DELETE',
      success: function() {
        parentEl.slideUp(400, function() { parentEl.remove(); });
      }
    })
  }

  function toggleEditingState() {
    var parentEl = $(this).parent().parent();
    parentEl.toggleClass('contact-row--editing');

    var contactName = parentEl.find('[contact-name-paragraph]').text();
    var contactPhone = parentEl.find('[contact-phone-paragraph]').text();

    parentEl.find('[contact-name-input]').val(contactName);
    parentEl.find('[contact-phone-input]').val(contactPhone);
  }

  function copyPhone() {
      var contactPhone = $(this).parent().find('[contact-phone-paragraph]').text();
      // $(this).parent().parent().find('[name="phone-number"]').val(contactPhone);
      $('[name="phone-number"]').val(contactPhone);
  }

  function toggleNewContact() {
      $('[contact-new-form]').toggleClass('contact-new-form-show');
      $('[contact-new-button]').toggleClass('contact-new-button-hide');
  }

  function handleAddContactRequest(event) {
      event.preventDefault();

      var contactName = $('[contact-new-form] [contact-name-section] [contact-name-input]').val();
      var contactPhone = $('[contact-new-form] [contact-phone-section] [contact-phone-input]').val();

      var requestUrl = apiRoot + 'contact/create';

      $.ajax({
          url: requestUrl,
          method: 'POST',
          processData: false,
          contentType: "application/json; charset=utf-8",
          dataType: 'json',
          data: JSON.stringify({
              fullName: contactName,
              phoneNumber: contactPhone
          }),
          complete: function(data) {
              if (data.status === 200) {
                  $('[contact-new-form] [contact-name-section] [contact-name-input]').val("");
                  $('[contact-new-form] [contact-phone-section] [contact-phone-input]').val("");
                  getAllContacts();
                  toggleNewContact();
                  $('[contacts-block]').toggleClass('contacts-show');
              }
          }
      });
  }

  $('[sms-send-form]').on('submit', handleSmsSubmitRequest);
  $('[contacts-button]').on('click', getAllContacts);
  $('[contact-new-button]').on('click', toggleNewContact);
  $('[contact-new-form]').on('submit', handleAddContactRequest);
  $('[contact-new-cancel-button]').on('click', toggleNewContact);


  contactsContainer.on('click','[contact-edit-button]', toggleEditingState);
  contactsContainer.on('click','[contact-edit-abort-button]', toggleEditingState);
  contactsContainer.on('click','[contact-submit-update-button]', handleContactUpdateRequest);
  contactsContainer.on('click','[contact-delete-button]', handleContactDeleteRequest);

  contactsContainer.on('click','[contact-name-section]', copyPhone);
  contactsContainer.on('click','[contact-phone-section]', copyPhone);

});