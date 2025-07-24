@user_management @regression
Feature: Add User in OrangeHRM

  Scenario Outline: Admin adds a new user with valid details
    Given the admin is logged in to OrangeHRM
    And the admin navigates to the Admin - User Management - Users page
    When the admin clicks the Add button
    And fills in the user details with:
      | userRole    | "<userRole>"    |
      | employee    | "<employee>"    |
      | status      | "<status>"      |
      | username    | "<username>"    |
      | password    | "<password>"    |
      | confirmpass | "<confirmpass>" |
    And clicks the Save button
    Then the new user "<username>" should be present in the user list

    Examples:
      | userRole | employee     | status   | username     | password   | confirmpass |
      | ESS      | Linda Jane   | Enabled  | testuser01   | Pass@1234  | Pass@1234   |
      | Admin    | Paul Collings| Disabled | testuser02   | Secret123! | Secret123!  |
